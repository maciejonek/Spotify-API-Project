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

import static com.projekt.spotifyApiDownloader.Tool.Converter.convertEntityToString;
import static com.projekt.spotifyApiDownloader.Tool.RequestSender.sendRequest;

public class Authenticator {
    private static final String clientId = System.getenv("SPOTIFY_CLIENT");
    private static final String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static final String redirectUri = "http://localhost:8080/user/callback";

    public static String generateAuthURL() {
        String scopes = "user-read-private user-read-email user-library-read playlist-modify-private playlist-modify-public";
        String encodedScopes = URLEncoder.encode(scopes, StandardCharsets.UTF_8);
        return String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s",
                clientId, encodedScopes, redirectUri);
    }

    public static JSONObject getTokenJson(String authCode){
            URI tokenURI = URI.create("https://accounts.spotify.com/api/token");

            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

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



}