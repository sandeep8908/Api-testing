package com.example.test.demo.api.engine.endpoins;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

    public static Response getAllUsers(){
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured.given().when().get(Routes.post_uri);
        return  response;
    }

    public static Response getUser(int userId){
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured.given().pathParam("userId", userId)
                .when().get(Routes.get_put_delete_uri);
        return response;
    }

    public static Response addUser(String payload){
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .when().post(Routes.post_uri);
        return response;
    }

    public static Response updateUser(int userId,String payload){
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("userId", userId).body(payload)
                .when().put(Routes.get_put_delete_uri);
        return response;
    }

    public static Response deleteUser(int userId){
        RestAssured.baseURI = Routes.base_uri;
        Response response = RestAssured.given().pathParam("userId", userId)
                .when().delete(Routes.get_put_delete_uri);
        return response;
    }
}
