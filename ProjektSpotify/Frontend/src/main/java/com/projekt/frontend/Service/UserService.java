package com.projekt.frontend.Service;
import com.projekt.frontend.Entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserService {
    RestClient restClient;
    public static final String BACKEND_URL = "http://localhost:8081";
    public static final String API_URL = "http://localhost:8080";

    public UserService(){
        this.restClient = RestClient.create();
    }
    public List<User> findAllUsers(){
        return restClient
                .get()
                .uri(BACKEND_URL + "/user/all")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
    public String SpotifyURL(){
        return restClient
                .get()
                .uri(API_URL + "/user/spotify")
                .retrieve()
                .body(String.class);
    }
}
