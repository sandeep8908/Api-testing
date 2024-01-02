package com.example.test.demo.api.test;

import com.example.test.demo.api.engine.endpoins.UserEndpoints;
import com.example.test.demo.api.payload.User;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class TestEndpoint {
    User userPayload;
    Faker faker;
    String status = "active";

    @BeforeAll
     void beforeTest() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setUserId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setPassword(faker.internet().password());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setStatus("active");
        System.out.println("**********************************************************************");
    }

    @Test
     void addUser() throws JSONException {
        Response response = postUser();
        JSONObject jsonObject = returnJsonObject(response);
        String userId = jsonObject.getString("userId");

        System.out.println("saved userId:-------- " + Integer.parseInt(userId));
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        assertThat(status,equalToIgnoringCase(jsonObject.getString("status")));
        System.out.println("**********" + this.userPayload.getUsername() + " is created ************");
    }

    @Test
     void getAllUser(){
        postUser();

        System.out.println("*************************{GET}****************************************");

        Response response = UserEndpoints.getAllUsers();
        response.then().log().body().statusCode(200);
    }

    @Test
     void getUsernameById() throws JSONException {
        Response postResponse = postUser();
        JSONObject jsonObject = returnJsonObject(postResponse);
        String userId = jsonObject.getString("userId");
        System.out.println("*************************{GET}****************************************");

        Response response = UserEndpoints.getUser(Integer.parseInt(userId));
        response.then().log().body().statusCode(200);

        assertThat(userPayload,hasProperty("password"));
        System.out.println("************  " + this.userPayload.getUserId() + " is fetched **********");
    }

    @Test
     void updateUser() throws JSONException {
        Response postResponse = postUser();
        JSONObject postJsonObject = returnJsonObject(postResponse);
        String userId = postJsonObject.getString("userId");
        System.out.println("saved userId:-------- " + userId);

        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("username", userPayload.getUsername());
        bodyParams.put("password", userPayload.getPassword());
        bodyParams.put("email", userPayload.getEmail());
        bodyParams.put("status", "inactive");

        String payload = new Gson().toJson(bodyParams);

        System.out.println("*************************{UPDATE}************************************");

        Response response = UserEndpoints.updateUser(Integer.parseInt(userId), payload);
        response.then().log().body().statusCode(200);

        Response afterUpdateResponse = UserEndpoints.getUser(this.userPayload.getUserId());
        afterUpdateResponse.then().log().body().statusCode(200);

        JSONObject jsonObject = returnJsonObject(afterUpdateResponse);
        User user = new Gson().fromJson(String.valueOf(jsonObject), User.class);

        assertThat(jsonObject.getString("status"), equalTo("inactive"));
        assertThat(user,hasProperty("status",equalTo("inactive")));

        System.out.println("*********  " + this.userPayload.getUserId() + " is updated ************");

    }

    @Test
     void deleteUser() throws JSONException {
        Response postResponse = postUser();
        JSONObject jsonObject = returnJsonObject(postResponse);
        String userId = jsonObject.getString("userId");

        System.out.println("*************************{DELETE}************************************");
        Response response = UserEndpoints.deleteUser(Integer.parseInt(userId));
        response.then().log().body().statusCode(200);

        System.out.println("********  " + this.userPayload.getUserId() + " is deleted *************");
    }

    private Response postUser() {
        Map<String, Object> bodyPayload = new HashMap<>();
        bodyPayload.put("userId", userPayload.getUserId());
        bodyPayload.put("username", userPayload.getUsername());
        bodyPayload.put("password", userPayload.getPassword());
        bodyPayload.put("email", userPayload.getEmail());
        bodyPayload.put("status", userPayload.getStatus());

        String payload = new Gson().toJson(bodyPayload);
        System.out.println("*************************{POST}***************************************");
        Response response = UserEndpoints.addUser(payload);
        response.then().log().all();
        return response;
    }

    private static JSONObject returnJsonObject(Response response) throws JSONException {
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject;
    }

}
