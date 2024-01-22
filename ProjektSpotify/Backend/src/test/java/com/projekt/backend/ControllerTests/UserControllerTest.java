package com.projekt.backend.ControllerTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class UserControllerTest {
    private static final String URI = "http://localhost:8081/user";

    @Test
    public void testGetUserById() {
        when()
                .get(URI + "/get?id=2")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }
}
