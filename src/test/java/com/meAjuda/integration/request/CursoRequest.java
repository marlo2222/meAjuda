package com.meAjuda.integration.request;

public class CursoRequest {

    public String createCurso(String descricaoCurso, String nomeCurso, String qtdSemestres){
        String body = String.format("{" +
                "\"descricao\": \"%s\"," +
                "\"disciplinas\": []," +
                "\"id\": 0," +
                "\"nome\": \"%s\"," +
                "\"qtdSesmestres\": %s" +
                "}", descricaoCurso, nomeCurso, qtdSemestres);
        return body;
    }
    public String updateCurso(String novaDescricaoCurso, String nomeCurso, String qtdSemestres, String idCurso){
        String body = String.format("{" +
                "\"descricao\": \"%s\"," +
                "\"disciplinas\": []," +
                "\"id\": %s," +
                "\"nome\": \"%s\"," +
                "\"qtdSesmestres\": %s" +
                "}", novaDescricaoCurso, idCurso, nomeCurso, qtdSemestres);
        return body;
    }
}
