package com.meAjuda.integration.base;

import static io.restassured.RestAssured.*;

import com.meAjuda.integration.config.BaseConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

public class BaseRequest extends BaseConfig {

    public HashMap<String, String> params = new HashMap<>();

    //GET
    public Response getMethod(String path){
        Response response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                .when()
                        .get(path).prettyPeek()
                .then()
                        .extract().response();
        return response;
    }
    //POST
    public Response postMethod(String path, String body){
        Response response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(body)
                .when()
                        .post(path).prettyPeek()
                .then()
                        .log().all()
                        .extract().response();
        return response;
    }
    //PUT
    public Response putMethod(String path, String body){
        Response response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(body)
                .when()
                        .put(path).prettyPeek()
                .then()
                        .log().all()
                        .extract().response();
        return response;
    }
    //DELETE
    public Response deleteMehod(String path, String body){
        Response response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(body)
                .when()
                        .delete(path).prettyPeek()
                .then()
                        .log().all()
                        .extract().response();
        return response;
    }

    public void addParam(String key, String value){
        params.put(key, value);
    }
}
