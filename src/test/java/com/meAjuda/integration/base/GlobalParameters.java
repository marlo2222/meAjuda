package com.meAjuda.integration.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalParameters {

    private static Properties properties;
    public static String BASE_URL_DISCIPLINAS;
    public static String PATH_DISCIPLINA;
    public static String PATH_CURSO;
    public static String END_DISCIPLINAS_LISTAR;
    public static String END_DISCIPLINAS_SALVAR;
    public static String END_DISCIPLINAS_ATUALIZAR;
    public static String END_DISCIPLINAS_DELETAR;
    public static String END_CURSO_LISTAR;
    public static String END_CURSO_SALVAR;
    public static String END_CURSO_DELETAR;
    public static String END_CURSO_ATUALIZAR;

    public GlobalParameters(){
        this.properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-api.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
        }

        this.BASE_URL_DISCIPLINAS = properties.getProperty("base-url.disciplinas");
        this.END_DISCIPLINAS_LISTAR = properties.getProperty("disciplina.listar");
        this.END_DISCIPLINAS_SALVAR = properties.getProperty("disciplina.salvar");
        this.END_DISCIPLINAS_ATUALIZAR = properties.getProperty("disciplina.atualizar");
        this.END_DISCIPLINAS_DELETAR = properties.getProperty("disciplina.deletar");

        this.END_CURSO_LISTAR = properties.getProperty("curso.listar");
        this.END_CURSO_SALVAR = properties.getProperty("curso.salvar");
        this.END_CURSO_DELETAR = properties.getProperty("curso.deletar");
        this.END_CURSO_ATUALIZAR = properties.getProperty("curso.atualizar");

        this.PATH_DISCIPLINA = properties.getProperty("path.disciplina");
        this.PATH_CURSO = properties.getProperty("path.curso");
    }

    public Properties getProperties(){
        return this.properties;
    }
}
