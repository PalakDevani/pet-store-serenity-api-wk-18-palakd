package com.petstore.petstoreinfo;

import com.petstore.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import io.vavr.collection.HashMap;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PetCRUDTest extends TestBase {

    static int id = 18;
    static HashMap<Object, Object> category;
    static String name = "aga";
    static List<String> photoUrls;
    static List<HashMap<String, String>> tags;
    static String status = "available";
    static int newId;
    static int generatedId;
    @Steps
    PetSteps petSteps;

    @Title("This method will create  a pet")
    @Test
    public void test001() {
        //This map data is to add  pair in category
        java.util.HashMap<Object, Object> data = new java.util.HashMap<Object, Object>();
        data.put("id", "9");
        data.put("name", "dog");

        List<java.util.HashMap<Object, Object>> tag = new ArrayList<>();

        tag.add(data);
        List<String> photoUrl = new ArrayList<String>();
        photoUrl.add("String");


        ValidatableResponse response = petSteps.createPat(id, data, name, photoUrl, tag, status);
        response.log().all().statusCode(200);

    }

    @Title("This method will verify that pet is created")
    @Test
    public void test002() {
        java.util.HashMap<String, Object> patMap = petSteps.getPetById(id);
        Assert.assertThat(patMap, hasValue("aga"));
        int generatedId = (int) patMap.get("id");
    }

    @Title("This method will update a pet")
    @Test
    public void test003() {
        name = name + "123";
        //This is for category field
        java.util.HashMap<Object, Object> data = new java.util.HashMap<Object, Object>();
        data.put("id", "9");
        data.put("name", "dog");
// this if for tags
        List<java.util.HashMap<Object, Object>> tag = new ArrayList<>();
        tag.add(data);
        List<String> photoUrl = new ArrayList<String>();
        photoUrl.add("String23");
        petSteps.updatePetById(id, data, name, photoUrl, tag, status).log().all().statusCode(200);
    }

    @Title("This method will delete pet")
    @Test
    public void test004() {
        petSteps.deletePet(id).statusCode(200);

    }
}
