package com.datadriven;

import com.dto.User;
import com.endpoints.UserEndpoint;
import com.util.BaseUtil;
import com.util.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
@Listeners(BaseUtil.class)
public class UserDDT {

    User user;

    @Test(priority = 1, dataProvider = "userdata", dataProviderClass = DataProviders.class)
    public void ddt(String id, String username, String firstName,
                    String lastName, String email,
                    String password, String phone,
                    String userStatus) {

        long parsedId = new BigDecimal(id).longValue();
        long parseUserStatus = new BigDecimal(userStatus).longValue();

        user = User.builder().id(parsedId).username(username)
                .firstName(firstName)
                .lastName(lastName).email(email)
                .password(password).phone(phone)
                .userStatus(parseUserStatus).build();

        Response response = UserEndpoint.createUser(user);
        response.print();

    }

    @Test(priority = 2, dataProvider = "usernames", dataProviderClass = DataProviders.class)
    public void ddt2(String username){
        Response response = UserEndpoint.getUser(username);
        response.print();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
