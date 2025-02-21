package com.regression;

import com.dto.User;
import com.endpoints.UserEndpoint;
import com.github.javafaker.Faker;
import com.util.BaseUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Optional;

@Listeners(BaseUtil.class)
public class UserTest {
    Faker faker;
    User user;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        user = User.builder().id(faker.idNumber().hashCode())
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().safeEmailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().cellPhone())
                .userStatus(0)
                .build();

    }

    @Test(priority = 1)
    public void createUser(){
        Response response  = UserEndpoint.createUser(user);
        response.print();
        response.then().statusCode(200);
        Assert.assertEquals(Optional.ofNullable(response.jsonPath().get("message")).get(), String.valueOf(user.getId()));
    }

    @Test(priority = 2, dependsOnMethods = {"createUser"})
    public void readUser(){
        Response response = UserEndpoint.getUser(user.getUsername());
        response.print();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser(){
        user.setFirstName("updated");
        user.setEmail("updated@gmail.com");
        Response response = UserEndpoint.updateUser(user, user.getUsername());
        response.print();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = {"createUser"})
    public void deleteUser(){
        Response response = UserEndpoint.deleteUser(user.getUsername());
        response.print();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
