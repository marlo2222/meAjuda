package com.meAjuda.integration.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalParameters {

    private static Properties properties;
    public static String BASE_URL_DISCIPLINAS;
    public static String END_DISCIPLINAS_LISTAR;
    public static String END_DISCIPLINAS_SALVAR;
    public static String END_DISCIPLINAS_ATUALIZAR;
    public static String END_DISCIPLINAS_DELETAR;

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
    }

    public Properties getProperties(){
        return this.properties;
    }
}
