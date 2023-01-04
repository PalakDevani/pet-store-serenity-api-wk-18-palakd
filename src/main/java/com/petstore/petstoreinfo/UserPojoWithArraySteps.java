package com.petstore.petstoreinfo;

import com.petstore.constants.EndPoints;
import com.petstore.models.UserPojoWithArray;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class UserPojoWithArraySteps {

    @Step
    public ValidatableResponse createUser(List<HashMap<Object, Object>> step) {


        UserPojoWithArray userPojoWithArray = new UserPojoWithArray();
        userPojoWithArray.setList(step);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(userPojoWithArray)
                .post(EndPoints.CREATE_USER)
                .then();

    }


}
