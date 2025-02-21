package com.endpoints;

import com.dto.User;
import com.util.ExtentReportUtil;
import com.util.LogUtil;
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
            ExtentReportUtil.getInstance().extentPass(response.asPrettyString());
            LogUtil.getInstance().logInfo(response.asPrettyString());
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
            ExtentReportUtil.getInstance().extentPass(response.asPrettyString());
            LogUtil.getInstance().logInfo(response.asPrettyString());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Response updateUser(User user, String username){
        Response response = null;
        try {
            response= given().
                    accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .pathParams("username", username )
                    .body(user)
                    .when()
                    .put(Routes.UPDATE_USER);
            ExtentReportUtil.getInstance().extentPass(response.asPrettyString());
            LogUtil.getInstance().logInfo(response.asPrettyString());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Response deleteUser(String username){
        Response response=null;
        try {
            response = given().
                    pathParams("username", username)
                    .when()
                    .delete(Routes.DELETE_USER);
            ExtentReportUtil.getInstance().extentPass(response.asPrettyString());
            LogUtil.getInstance().logInfo(response.asPrettyString());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
