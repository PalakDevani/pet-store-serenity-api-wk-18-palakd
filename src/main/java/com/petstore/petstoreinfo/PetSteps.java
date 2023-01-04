package com.petstore.petstoreinfo;

import com.petstore.constants.EndPoints;
import com.petstore.models.PetPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class PetSteps {
    /*{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}*/
    @Step("Creating a pet with Id:{0},category:{1},name:{2},photoUrls:{3},tags:{4},ststus:{5}")
    public ValidatableResponse createPat(int id, HashMap<Object, Object> category, String name, List<String> photoUrls, List<HashMap<Object, Object>> tags, String status) {
        //This map data is to add  pair in category
//        HashMap<Object,Object> data=new HashMap<>();
//        data.put("id","3");
//        data.put("name","sagar");

        PetPojo petPojo = new PetPojo();

        petPojo.setId(id);
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")

                .when()
                .body(petPojo)
                .post(EndPoints.CREATE_PET)
                .then();

    }

    @Step("Getting a pet by id:{0}")
    public HashMap<String, Object> getPetById(int id) {
        String s1 = "findAll{it.id==";
        String s2 = "}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.CREATE_PET)
                .then().statusCode(200)
                .extract()
                .path(s1 + id + s2);

    }

    @Step("Upadating a pet by id:{0},category:{1},name:{2},photoUrls:{3},tags:{4},status:{5}")

    public ValidatableResponse updatePetById(int id, HashMap<Object, Object> category, String name, List<String> photoUrls, List<HashMap<Object, Object>> tags, String status) {
        PetPojo petPojo = new PetPojo();

        petPojo.setId(id);
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .headers("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(petPojo)
                .when()
                .put(EndPoints.UPDATE_PET)
                .then();
    }

    @Step("Deleting a pet with id:{0}")
    public ValidatableResponse deletePet(int id) {
        return SerenityRest.given().log().all()
                .pathParam("petId", id)
                .when()
                .delete(EndPoints.DELETE_PET_BY_ID)
                .then();
    }

}
