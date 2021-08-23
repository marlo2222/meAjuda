package com.meAjuda.integration.base;

public class Enviroment {

    private String base;

    public Enviroment(){
        this.base = GlobalParameters.BASE_URL_DISCIPLINAS;
    }

    public String listDisciplinas(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.END_DISCIPLINAS_LISTAR)
                .toString();
    }

    public String envId(String path, String id){
        return new StringBuilder()
                .append(path)
                .append("/")
                .append(id)
                .toString();
    }
}
