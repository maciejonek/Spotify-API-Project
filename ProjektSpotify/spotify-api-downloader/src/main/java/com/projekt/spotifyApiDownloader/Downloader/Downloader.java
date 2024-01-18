package com.projekt.spotifyApiDownloader.Downloader;

import com.projekt.spotifyApiDownloader.Entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.projekt.spotifyApiDownloader.Tool.RequestSender.sendRequest;

public class Downloader {
    public JSONObject getSpotifyJSON(User user, String url){
        String accessToken = user.getAuthToken();
        URI userURL = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(userURL)
                .header("Authorization","Bearer " + accessToken)
                .build();

        return sendRequest(request);
    }

    private static HttpResponse<String> getSpotifyString(URI uri, String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer " + token)
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
