package com.projekt.spotifyApiDownloader.Downloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.spotifyApiDownloader.DTO.TrackDTO;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.DTO.ItemsDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TrackDownloader {

    public static List<Track> getTracksFromPlaylist(Playlist playlist, User user){
        try {
            URI tracksURI = URI.create("https://api.spotify.com/v1/playlists/"+playlist.getPlaylistId()+"/tracks");
            String accessToken = user.getAuthToken();
            HttpResponse<String> response = callForJSON(tracksURI,accessToken);
            ObjectMapper objectMapper = new ObjectMapper();
            ItemsDTO itemsDTO = objectMapper.readValue(response.body(), ItemsDTO.class);
            return itemsDTO.getItems().stream().map(item-> {
                TrackDTO trackDTO = item.getTrackDTO();
                return new Track(trackDTO.getTrackId(), trackDTO.getName(), trackDTO.getAlbum().getImages().get(0).getUrl(),trackDTO.getUri());
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
