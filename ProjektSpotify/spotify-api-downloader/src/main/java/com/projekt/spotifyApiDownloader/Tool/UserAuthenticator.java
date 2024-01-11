package com.projekt.spotifyApiDownloader.Tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.Entity.User;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UserAuthenticator {
    private static final String clientId = System.getenv("SPOTIFY_CLIENT");
    private static final String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static final String redirectUri = "http://localhost:8080/user/callback";

    public static String generateAuthURL() {
        String scopes = "user-read-private user-read-email user-library-read";
        String encodedScopes = URLEncoder.encode(scopes, StandardCharsets.UTF_8);
        return String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s",
                clientId, encodedScopes, redirectUri);
    }

    public static JSONObject getTokenJson(String authCode){
            URI tokenURI = URI.create("https://accounts.spotify.com/api/token");

            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials;
            encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            String requestBody = "grant_type=authorization_code" +
                    "&code=" + authCode + "&redirect_uri=" + redirectUri;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(tokenURI)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + encodedCredentials)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            return sendRequest(request);
    }
    public static JSONObject getUserJson(User user){
            String accessToken = user.getAuthToken();
            URI userURL = URI.create("https://api.spotify.com/v1/me");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(userURL)
                    .header("Authorization","Bearer " + accessToken)
                    .build();
        return sendRequest(request);
    }

    private static JSONObject sendRequest(HttpRequest request){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.body()!=null) return new JSONObject(response.body());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void userJsonToPlaylistController(User user){
        Converter<User> converter = new Converter<>(user);
        String userString = converter.convertEntityToString();
        URI playlistUri = URI.create("http://localhost:8080/playlist/download");
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(playlistUri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}