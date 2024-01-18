package com.projekt.spotifyApiDownloader.Downloader;

import com.projekt.spotifyApiDownloader.Entity.User;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpRequest;

import static com.projekt.spotifyApiDownloader.Tool.Converter.convertEntityToString;
import static com.projekt.spotifyApiDownloader.Tool.RequestSender.sendRequest;

public class UserDownloader {
    private User user;

    public UserDownloader(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JSONObject getUserJson(){
        String accessToken = user.getAuthToken();
        URI userURL = URI.create("https://api.spotify.com/v1/me");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(userURL)
                .header("Authorization","Bearer " + accessToken)
                .build();

        return sendRequest(request);
    }

    public  void userJsonToPlaylistController(){
        String userString = convertEntityToString(user);
        URI playlistUri = URI.create("http://localhost:8080/playlist/download");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(playlistUri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userString))
                .build();

        sendRequest(request);


    }
}
