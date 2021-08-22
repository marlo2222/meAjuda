package com.meAjuda.integration.test;

import com.meAjuda.integration.base.BaseRequest;
import com.meAjuda.integration.base.Enviroment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class DisciplinaTest extends BaseRequest {

    Enviroment env = new Enviroment();

    @Test(testName = "lista todas as disciplinas disponiveis", groups = {"disciplina", "integration"})
    public void listarTodasDisciplinasTest(){
        Response response = getMethod(env.listDisciplinas());
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.getList("id").size(), 9);
        assertEquals(path.getList("nome").get(0), "redes de computadores");
    }
}
