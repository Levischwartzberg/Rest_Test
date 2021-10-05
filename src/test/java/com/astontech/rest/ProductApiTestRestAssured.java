package com.astontech.rest;

import org.junit.jupiter.api.Test;
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
}
