package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    public UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public int testGet(){
        return service.getMappingTest();
    }
    @GetMapping("/spotify")
    public String getSpotifyURL(){
        return service.getSpotifyURL();
    }
    @GetMapping("/callback")
    public void connectUser(@RequestParam("code") String auth){
        service.addConnectedUserToDb(auth);
    }
}