package com.astontech.rest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

public class ProductApiTestRestAssured {

    @Test
    public void testEndpointShouldReturn200() {
        get("/test")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenUsePathParamValidId_thenOK() {
        given().pathParam("id", 1)
                .when().get("/product/{id}")
                .then().statusCode(200);
    }

    @Test
    public void whenUsePathParamInvalidId_thenNOT_FOUND() {
        given().pathParam("id", 9999)
                .when().get("/product/{id}")
                .then().statusCode(404);
    }

    @Test
    public void whenUseQueryParamValidSkuThenOK() {
        given().queryParam("sku", "TV-SAM-283601")
                .when().get("/product")
                .then().statusCode(200);
    }

    @Test
    public void whenFindBySkuAssertProductDescription() {
        given().queryParam("sku", "TV-SAM-283601")
                .when().get("/product")
                .then().statusCode(200)
                .assertThat()
                .body("description", equalTo("SAMSUNG 60\" TV"));
    }

    @Test public void getResponseTime() {
        System.out.println("Response Time: " + get("/product/").timeIn(TimeUnit.MILLISECONDS) + " ms.");
    }

    @Test public void getResponseContentType() {
        System.out.println("Content Type: " + get("/product/").then().extract().contentType());
    }

    @Test
    public void saveProductShouldReturnAnId() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("sku", "A17-223");
        requestBody.put("description", "Apple iPad");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .post("/product/")
                .then().statusCode(201)
                .assertThat()
                .body("$", hasKey("id"))
                .body("sku", equalTo("A17-223"))
                .body("description", equalTo("Apple iPad"));
    }

    @Test
    public void updateProductShouldReturnAnId() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 3);
        requestBody.put("sku", "A17-223");
        requestBody.put("description", "Apple iPad");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .put("/product/")
                .then().statusCode(202)
                .assertThat()
                .body("$", hasKey("id"))
                .body("sku", equalTo("A17-223"))
                .body("description", equalTo("Apple iPad"));
    }
}
