package com.projekt.frontend.Service;

import com.projekt.frontend.Entity.Track;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;

@Service
public class TrackService {
    RestClient restClient;
    public static final String BACKEND_URL = "http://localhost:8081";
    public static final String API_URL = "http://localhost:8080";

    public TrackService() {
        this.restClient = RestClient.create();
    }

    public Iterable<Track> getAllTracks(){
        return restClient.get()
                .uri(BACKEND_URL + "/track/all")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
