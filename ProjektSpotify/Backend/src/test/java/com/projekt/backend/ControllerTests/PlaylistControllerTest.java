package com.projekt.backend.ControllerTests;

import com.projekt.backend.Entity.Playlist;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistControllerTest {
    private static final String URI = "http://localhost:8081/playlist";

    @Test
    public void testGetUsersPlaylists() {
        when()
                .get(URI + "/user/1")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }
    @Test
    public void testGetPlaylist(){
        when()
                .get(URI + "/info/1")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }

    @Test
    public void testAddTrackToPlaylist(){
        when()
                .post(URI + "/add?id=6&track=23&uri=spotify:track:6Zj3YsYfj8YTY9aGMDnpn8&user=2")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }

    @Test
    public void testRemoveTrackToPlaylist(){
        when()
                .delete(URI + "/delete?id=6&track=23&uri=spotify:track:6Zj3YsYfj8YTY9aGMDnpn8&user=2")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .body();
    }

    @Test
    public void testEditPlaylistDetails(){
        Playlist playlist = new Playlist();
        playlist.setDatabaseId(6L);
        playlist.setPlaylistId("2esMRwILK2iDQoYZNzgb78");
        playlist.setName("testplaylist1");
        playlist.setDescription("controller test");
        playlist.setPublic(true);

        with()
                .body(playlist)
                .contentType("application/json")
                .when()
                .put(URI + "/edit?user=2")
                .then()
                .statusCode(200)
                .log()
                .body();
    }




}
