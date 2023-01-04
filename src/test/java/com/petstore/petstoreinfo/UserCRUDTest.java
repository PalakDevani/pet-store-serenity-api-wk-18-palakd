package com.petstore.petstoreinfo;

import com.petstore.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static int id = 18;
    static String username = "Paro";
    static String firstName = "Krishiv";
    static String lastName = "Patel";

    static String email = "pkrish@gmail.com";
    static String password = "pkrish123";
    static String phone = "07892354689";
    static int userStatus = 0;
    @Steps
    UserPojoWithArraySteps userPojoWithArraySteps;
    @Steps
    UserSteps userSteps;

    @Title("This method will create a user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(id, username, firstName, lastName, email, password, phone, userStatus);
        response.log().all().statusCode(200);
    }

    @Title("This method will verify that user is created")
    @Test
    public void test002() {
        ValidatableResponse response = userSteps.getUserByUserName(username).statusCode(200);
        HashMap<String, Object> newUser = response.extract().path("");
        Assert.assertThat(newUser, hasValue(username));

    }

    @Title("This method will update a user and verify that user is created")
    @Test
    public void test003() {

        lastName = lastName + "_add";
        ValidatableResponse response = userSteps.updateUserByUserName(id, username, firstName, lastName, email, password, phone, userStatus).statusCode(200);
        HashMap<String, Object> updatedUser = response.extract().path("");
        System.out.println(updatedUser);
        Integer newId = new Integer(id);
        String id = newId.toString();
        Assert.assertTrue(updatedUser.containsValue(id));
    }

    @Title("This method will delete user")
    @Test
    public void test004() {
        ValidatableResponse response = userSteps.deleteUserWithUserName(username).statusCode(200);
        response = userSteps.getUserByUserName(username).statusCode(404);
    }
}
