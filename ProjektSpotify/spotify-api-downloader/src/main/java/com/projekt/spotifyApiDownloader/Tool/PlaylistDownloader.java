package com.projekt.spotifyApiDownloader.Tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.FromJsonObjects.PlaylistResponse;
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

    public static List<Playlist> getSpotifyPlaylist(User user){
        try {
            URI playlistURI = new URI("https://api.spotify.com/v1/me/playlists?limit=1");
            URI trackURI = new URI("http://localhost:8080/track/download");
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
                    sendPlaylistToTrackController(trackURI,playlist,user);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            return playlistEntities;
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
    private static void sendPlaylistToTrackController(URI uri, Playlist playlist, User user) throws IOException, InterruptedException {
         HttpClient client = HttpClient.newHttpClient();

            JSONObject requestJson = new JSONObject();
            requestJson.put("user", new JSONObject(user));
            requestJson.put("playlist", new JSONObject(playlist));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestJson.toString()))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
