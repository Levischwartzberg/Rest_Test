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
}
