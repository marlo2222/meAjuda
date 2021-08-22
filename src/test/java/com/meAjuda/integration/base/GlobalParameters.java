package com.meAjuda.integration.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalParameters {

    private static Properties properties;
    public static String BASE_URL_DISCIPLINAS;
    public static String END_DISCIPLINAS_LISTAR;

    public GlobalParameters(){
        this.properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-api.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
        }

        this.BASE_URL_DISCIPLINAS = properties.getProperty("base-url.disciplinas");
        this.END_DISCIPLINAS_LISTAR = properties.getProperty("disciplina.listar");
    }

    public Properties getProperties(){
        return this.properties;
    }
}
