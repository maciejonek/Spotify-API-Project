package com.projekt.spotifyApiDownloader.Tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Response.PlaylistResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PlaylistDownloader {
    private static final String clientId = System.getenv("SPOTIFY_CLIENT");
    private static final String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static final String redirectUri = "http://localhost:8080/user/callback";

    public static JSONObject getSpotifyPlaylist(User user){
        try {
            URI playlistURI = new URI("https://api.spotify.com/v1/me/playlists?limit=1");
            URI trackURI = new URI("http://localhost:8080/track/from/playlist");
            String accessToken = user.getAuthToken();

            HttpResponse<String> response = callForJSON(playlistURI,accessToken);

            ObjectMapper objectMapper = new ObjectMapper();
            PlaylistResponse playlistResponse = objectMapper.readValue(response.body(),PlaylistResponse.class);

            List<Playlist> playlistEntities = playlistResponse.getPlaylists().stream().map(
                    playlistObject -> new Playlist(
                            playlistObject.getPlaylistId(),
                            playlistObject.getName(),
                            playlistObject.getDescription()
                    )
            ).toList();

            playlistEntities.forEach(playlist -> {
                try {
                    sendPlaylistToTrackController(trackURI,playlist);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            return new JSONObject(response.body());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private static HttpResponse<String> callForJSON(URI uri, String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer " + token)
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private static void sendPlaylistToTrackController(URI uri, Playlist playlist) throws IOException, InterruptedException {
            String playlistString = convertPlaylistObjectToJson(playlist);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(playlistString))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String convertPlaylistObjectToJson(Playlist playlist){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(playlist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
