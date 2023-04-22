package com.meAjuda.integration.test;

import com.meAjuda.integration.base.BaseRequest;
import com.meAjuda.integration.base.Enviroment;
import com.meAjuda.integration.request.CursoRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(testName = "Testes de integração -> CURSO")
public class CursoTest extends BaseRequest {

    Enviroment env = new Enviroment();
    CursoRequest body = new CursoRequest();
    String descricaoCurso;
    String novaDescricaoCurso;
    String nomeCurso;
    String qtdSemestre;
    String idCursoCriado;

    @BeforeClass
    public void massa(){
        this.descricaoCurso = "Curso preparatorio para o mercado de turismo";
        this.novaDescricaoCurso = "Preparação para o mercado de hotelaria";
        this.nomeCurso = "Hotelaria";
        this.qtdSemestre = "8";
    }

    @Test(testName = "Lista todos os cursos disponiveis", groups = {"curso", "integration"}, priority = 1)
    public void listarAllCursosTest(){
        Response response = getMethod(env.listCursos());
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.getList("id").size(), 4);
    }

    @Test(testName = "Cadastra um novo curso", groups = {"curso", "integration"}, priority = 2)
    public void cadastrarNovoCursoTest(){
        Response response = postMethod(env.saveCurso(), body.createCurso(descricaoCurso, nomeCurso, qtdSemestre));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        assertEquals(path.get("nome").toString().toLowerCase(), nomeCurso.toLowerCase());
        assertEquals(path.get("descricao").toString().toLowerCase(), descricaoCurso.toLowerCase());
        assertEquals(path.get("qtdSesmestres").toString().toLowerCase(), qtdSemestre.toLowerCase());
        this.idCursoCriado = path.get("id").toString();
    }

    @Test(testName = "Lista um curso pelo seu ID", groups = {"curso", "integration"}, priority = 3)
    public void listarCursoTest(){
        Response response = getMethod(env.listCursosByID(idCursoCriado));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.get("nome").toString().toLowerCase(), nomeCurso.toLowerCase());
        assertEquals(path.get("descricao").toString().toLowerCase(), descricaoCurso.toLowerCase());
        assertEquals(path.get("qtdSesmestres").toString().toLowerCase(), qtdSemestre.toLowerCase());
    }
    @Test(testName = "Atualiza as informações de um curso", groups = {"curso", "integration"}, priority = 4)
    public void atualizarCursoTest(){
        Response response = putMethod(env.updateCurso(), body.updateCurso(novaDescricaoCurso,nomeCurso, qtdSemestre, idCursoCriado));
        JsonPath path = response.jsonPath();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(path.get("nome").toString().toLowerCase(), nomeCurso.toLowerCase());
        assertEquals(path.get("descricao").toString().toLowerCase(), novaDescricaoCurso.toLowerCase());
        assertEquals(path.get("qtdSesmestres").toString().toLowerCase(), qtdSemestre.toLowerCase());
    }
    @Test(testName = "Deleta um curso pelo seu ID", groups = {"curso", "integration"}, priority = 5)
    public void deletarCursoTest(){
        Response response = deleteMehod(env.deleteCurso(idCursoCriado));
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
