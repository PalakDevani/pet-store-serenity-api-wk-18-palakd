package com.petstore.petstoreinfo;

import com.petstore.constants.EndPoints;
import com.petstore.models.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    @Step("Creating a user with id:{0},userName:{1},firstName:{2},lastName:{3},email:{4},password:{5},phoneNo:{6},userStatus:{7}")
    public ValidatableResponse createUser(int id, String userName, String firstName, String lastName, String email, String password, String phoneNo, int userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(userName);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phoneNo);
        userPojo.setUserStatus(userStatus);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Getting user by userName:{0}")
    public ValidatableResponse getUserByUserName(String userName) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("username", userName)
                .get(EndPoints.GET_USER_BY_USERNAME)
                .then().log().all();
    }

    @Step("Updating user by id:{0},userName:{1},firstName:{2},lastName:{3},email:{4},password:{5},phoneNo:{6},userStatus:{7}")
    public ValidatableResponse updateUserByUserName(int id, String userName, String firstName, String lastName, String email, String password, String phoneNo, int userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(userName);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phoneNo);
        userPojo.setUserStatus(userStatus);

        return SerenityRest.given().log().all()
                .pathParam("username", userName)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_USERNAME)
                .then();

    }
@Step("Deleting user with userName:{0}")
    public ValidatableResponse deleteUserWithUserName(String userName){
      return   SerenityRest.given().log().all()
                .pathParam("username", userName)
                .header("Content-Type", "application/json")
                .when()
                .delete(EndPoints.GET_USER_BY_USERNAME)
                .then();

}
}
