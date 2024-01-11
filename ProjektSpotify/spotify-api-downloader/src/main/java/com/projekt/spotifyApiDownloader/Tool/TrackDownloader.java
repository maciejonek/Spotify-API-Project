package com.projekt.spotifyApiDownloader.Tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.FromJsonObjects.ItemsResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TrackDownloader {

    private static final String clientId = System.getenv("SPOTIFY_CLIENT");
    private static final String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static final String redirectUri = "http://localhost:8080/user/callback";

    public static List<Track> getTracksFromPlaylist(Playlist playlist, User user){
        try {
            URI tracksURI = URI.create("https://api.spotify.com/v1/playlists/"+playlist.getPlaylistId()+"/tracks");
            String accessToken = user.getAuthToken();

            HttpResponse<String> response = callForJSON(tracksURI,accessToken);

            ObjectMapper objectMapper = new ObjectMapper();
            ItemsResponse itemsResponse = objectMapper.readValue(response.body(), ItemsResponse.class);

            return itemsResponse.getItems().stream().map(item-> {
                Track track = item.getTrack();
                track.getPlaylists().add(playlist);
                return track;
            }).toList();
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

}
