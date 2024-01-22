package com.projekt.backend.ControllerTests;

import com.projekt.backend.Entity.Track;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;

import static io.restassured.RestAssured.when;

public class TrackControllerTest {
    private static final String URI = "http://localhost:8081/track";

    @Test
    public void testGetAllTracks() {
        when()
                .get(URI + "/all")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }
}
