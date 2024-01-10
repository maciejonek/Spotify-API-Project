package com.projekt.spotifyApiDownloader.Tool;

import com.projekt.spotifyApiDownloader.Entity.User;
import org.json.JSONObject;

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

        //        System.out.println("Authorization URL: " + authorizationUrl);
        return String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s",
                clientId, encodedScopes, redirectUri);
    }

    public static JSONObject getTokenJson(String authCode){
        try {
            URI tokenURI = new URI("https://accounts.spotify.com/api/token");

            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials;
            encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            String requestBody = "grant_type=authorization_code" +
                    "&code=" + authCode + "&redirect_uri=" + redirectUri;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(tokenURI)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + encodedCredentials)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            return new JSONObject(response.body());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static JSONObject getUserJson(User user){
        try{
            String accessToken = user.getAuthToken();
            URI userURL = new URI("https://api.spotify.com/v1/me");
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(userURL)
                    .header("Authorization","Bearer " + accessToken)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            return new JSONObject(response.body());
//            System.out.println(object.getString("id"));
//            return object;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}