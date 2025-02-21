package com.endpoints;

import com.dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoint {

    public static Response createUser(User user){
        Response response = null;
        try {
            response = given()
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(user)
                    .when()
                    .post(Routes.POST_USER);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Response getUser(String username){
        try {
            Response response =  given().
                    accept(ContentType.JSON)
                    .pathParams("username", username )
                    .when()
                    .get(Routes.GET_USER);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Response updateUser(User user, String username){
        return given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParams("username", username )
                .body(user)
                .when()
                .put(Routes.UPDATE_USER);
    }

    public static Response deleteUser(String username){
        return given().
                pathParams("username", username)
                .when()
                .delete(Routes.DELETE_USER);
    }
}
