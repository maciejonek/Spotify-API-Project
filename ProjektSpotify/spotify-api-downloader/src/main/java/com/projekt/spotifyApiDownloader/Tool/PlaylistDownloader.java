package com.projekt.spotifyApiDownloader.Tool;

import com.projekt.spotifyApiDownloader.Entity.User;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class PlaylistDownloader {
    private static String clientId = System.getenv("SPOTIFY_CLIENT");
    private static String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static String redirectUri = "http://localhost:8080/user/callback";

    public static JSONObject getSpotifyPlaylist(User user){
        try {
            URI playlistURI = new URI("https://api.spotify.com/v1/me/playlists?limit=1");
            String accessToken = user.getAuthToken();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(playlistURI)
                    .header("Authorization","Bearer " + accessToken)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return new JSONObject(response.body());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
