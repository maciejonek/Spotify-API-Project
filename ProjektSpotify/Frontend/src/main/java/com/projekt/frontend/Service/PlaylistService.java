package com.projekt.frontend.Service;

import com.projekt.frontend.Entity.Playlist;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PlaylistService {
    RestClient restClient;
    public static final String BACKEND_URL = "http://localhost:8081";
    public static final String API_URL = "http://localhost:8080";

    public PlaylistService() {
        this.restClient = RestClient.create();
    }

    public List<Playlist> getPlaylists(Long userId){
        List<Playlist> playlists = restClient.get()
                .uri(BACKEND_URL + "/playlist/user/"+userId)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return playlists;
    }

    public Playlist getPlaylistInfo(Long id){
        return restClient.get()
                .uri(BACKEND_URL + "/playlist/info/" + id)
                .retrieve()
                .body(Playlist.class);
    }

    public void deleteTrack(Long id, Long track, String trackUri, Long userId){
        restClient.delete()
                .uri(BACKEND_URL + "/playlist/delete?id="+id+"&track=" + track + "&uri="+ trackUri + "&user=" + userId)
                .retrieve()
                .toEntity(String.class);
    }
    public void addTrack(Long id, Long track, String trackUri, Long userId){
        restClient.post()
                .uri(BACKEND_URL + "/playlist/add?id="+id+"&track=" + track + "&uri="+ trackUri + "&user=" + userId)
                .retrieve()
                .toEntity(String.class);
    }
    public void editDetails(Playlist playlist, Long userId){
        restClient.put()
                .uri(BACKEND_URL + "/playlist/edit?user=" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlist)
                .retrieve();
    }
}
