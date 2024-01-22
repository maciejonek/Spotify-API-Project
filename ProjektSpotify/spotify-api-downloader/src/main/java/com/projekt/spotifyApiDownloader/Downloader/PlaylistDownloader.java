package com.projekt.spotifyApiDownloader.Downloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.DTO.PlaylistDTO;
import com.projekt.spotifyApiDownloader.Tool.RequestSender;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.projekt.spotifyApiDownloader.Tool.Converter.convertEntityToString;

public class PlaylistDownloader {

    public static List<Playlist> getSpotifyPlaylist(User user){
        try {
            String playlistURL = "https://api.spotify.com/v1/me/playlists?limit=3";

            String accessToken = user.getAuthToken();
            Downloader downloader = new Downloader();
            JSONObject playlists = downloader.getSpotifyJSON(user,playlistURL);
            ObjectMapper objectMapper = new ObjectMapper();
            PlaylistDTO playlistDTO1 = objectMapper.readValue(playlists.toString(), PlaylistDTO.class);
            return playlistDTO1.getPlaylists().stream().map(
                    playlistObject -> new Playlist(
                                playlistObject.getPlaylistId(),
                                playlistObject.getName(),
                                playlistObject.getDescription(),
                                playlistObject.getPublic()
                        )
            ).toList();
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
    public static  void addTracksToPlaylist(User user){
        URI trackURI = URI.create("http://localhost:8080/track/download");
            try {
                sendPlaylistToTrackController(trackURI,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    private static void sendPlaylistToTrackController(URI uri, User user) throws IOException, InterruptedException {
            String userString = convertEntityToString(user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userString))
                    .build();
            RequestSender.sendRequest(request);
    }

}
