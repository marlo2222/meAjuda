package com.meAjuda.integration.base;

public class Enviroment {

    private String base;

    public Enviroment(){
        this.base = GlobalParameters.BASE_URL_DISCIPLINAS;
    }
    /*
    API DISCIPLINA
     */
    public String listDisciplinas(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_DISCIPLINA)
                .append(GlobalParameters.END_DISCIPLINAS_LISTAR)
                .toString();
    }
    public String saveDisciplina(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_DISCIPLINA)
                .append(GlobalParameters.END_DISCIPLINAS_SALVAR)
                .toString();
    }
    public String updateDisciplina(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_DISCIPLINA)
                .append(GlobalParameters.END_DISCIPLINAS_ATUALIZAR)
                .toString();
    }
    public String deleteDisciplina(String id){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_DISCIPLINA)
                .append(GlobalParameters.END_DISCIPLINAS_DELETAR)
                .append("/")
                .append(id)
                .toString();
    }
    /*
    API CURSO
     */
    public String listCursos(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_CURSO)
                .append(GlobalParameters.END_CURSO_LISTAR)
                .toString();
    }
    public String listCursosByID(String id){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_CURSO)
                .append(GlobalParameters.END_CURSO_LISTAR)
                .append("/")
                .append(id)
                .toString();
    }
    public String saveCurso(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_CURSO)
                .append(GlobalParameters.END_CURSO_SALVAR)
                .toString();
    }
    public String updateCurso(){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_CURSO)
                .append(GlobalParameters.END_CURSO_ATUALIZAR)
                .toString();
    }
    public String deleteCurso(String id){
        return new StringBuilder()
                .append(base)
                .append(GlobalParameters.PATH_CURSO)
                .append(GlobalParameters.END_CURSO_DELETAR)
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
