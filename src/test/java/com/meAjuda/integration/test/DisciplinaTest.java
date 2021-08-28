package com.meAjuda.integration.test;

import com.meAjuda.integration.base.BaseRequest;
import com.meAjuda.integration.base.Enviroment;
import com.meAjuda.integration.request.DisciplinaRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisciplinaTest extends BaseRequest {

    Enviroment env = new Enviroment();
    DisciplinaRequest body = new DisciplinaRequest();
    String idDisciplinaCriada;
    String idCurso;
    String nomeDisciplina;
    String novoNomeDisciplina;

    @BeforeClass
    public void massa(){
        this.nomeDisciplina = "quimica inorganica";
        this.novoNomeDisciplina = "quimica organica";
        this.idCurso = "7";
    }

    @Test(testName = "lista todas as disciplinas disponiveis", groups = {"disciplina", "integration"}, priority = 1)
    public void listarTodasDisciplinasTest(){
        Response response = getMethod(env.listDisciplinas());
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.getList("id").size(), 9);
        assertEquals(path.getList("nome").get(0), "redes de computadores");
    }

    @Test(testName = "Lista uma disciplina pelo seu ID", groups = {"disciplina", "integration"}, priority = 3)
    public void listarDisciplinaIdTest(){
        Response response = getMethod(env.envId(env.listDisciplinas(), idDisciplinaCriada));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.get("nome"), nomeDisciplina);
    }

    @Test(testName = "Cadastra uma nova disciplina", groups = {"disciplina", "integration"}, priority = 2)
    public void cadastrarDisciplinaTest(){
        Response response = postMethod(env.saveDisciplina(), body.createDisciplina(nomeDisciplina, idCurso));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        assertEquals(path.get("nome"), nomeDisciplina);
        idDisciplinaCriada = path.get("id").toString();
    }

    @Test(testName = "Atualiza uma disciplina existente", groups = {"disciplina", "integration"}, priority = 4)
    public void atualizaDisciplinaTest(){
        Response response = putMethod(env.updateDisciplina(), body.updateDisciplina(novoNomeDisciplina, idCurso, idDisciplinaCriada));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.get("id").toString(), idDisciplinaCriada);
        assertEquals(path.get("nome").toString(), novoNomeDisciplina);
    }

    @Test(testName = "Deleta uma disciplina existente", groups = {"disciplina", "integration"}, priority = 5)
    public void deletarDisciplinaTest(){
        Response response = deleteMehod(env.deleteDisciplina(idDisciplinaCriada));
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        //assertEquals(response.getBody().);
    }

}
