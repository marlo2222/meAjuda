package com.meAjuda.integration.request;

public class DisciplinaRequest {

    public String createDisciplina(String nomeDisciplina, String idCurso){
        String body = String.format("{" +
                "\"curso\":{" +
                    "\"descricao\":\"string\"," +
                    "\"disciplinas\":[{}]," +
                    "\"id\":%s," +
                    "\"nome\":\"string\"," +
                    "\"qtdSesmestres\":0" +
                "}," +
                "\"id\":0," +
                "\"nome\":\"%s\"" +
                "}",idCurso, nomeDisciplina);
        return body;
    }

    public String updateDisciplina(String novoNomeDisciplina, String idCurso, String idDisciplina){
        String body = String.format("{" +
                "\"curso\":{" +
                "\"descricao\":\"string\"," +
                "\"disciplinas\":[{}]," +
                "\"id\":%s," +
                "\"nome\":\"string\"," +
                "\"qtdSesmestres\":0" +
                "}," +
                "\"id\":%s," +
                "\"nome\":\"%s\"" +
                "}",idCurso,idDisciplina,novoNomeDisciplina);
        return body;
    }

}
