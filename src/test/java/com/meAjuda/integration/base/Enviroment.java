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
    public String saveDisciplina(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.END_DISCIPLINAS_SALVAR)
                .toString();
    }
    public String updateDisciplina(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.END_DISCIPLINAS_ATUALIZAR)
                .toString();
    }
    public String deleteDisciplina(String id){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.END_DISCIPLINAS_DELETAR)
                .append("/")
                .append(id)
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
