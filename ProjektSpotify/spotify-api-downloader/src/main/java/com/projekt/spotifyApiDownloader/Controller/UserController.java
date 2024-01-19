package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public void connectUser(@RequestParam("code") String auth, HttpServletResponse response, HttpSession session) throws IOException {
        service.addConnectedUserToDb(auth);
        response.sendRedirect("http://localhost:8082/index");
        session.setAttribute("sharedVariable", "Hello from Application 1!");
        System.out.println(1);
    }
}