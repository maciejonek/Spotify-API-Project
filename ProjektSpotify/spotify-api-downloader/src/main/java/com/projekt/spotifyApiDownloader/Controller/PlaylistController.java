package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Service.PlaylistService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {
    public PlaylistService playlistService;
    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/get50")
    public void getNewestPlaylist(){
        playlistService.getNewestPlaylist();
    }
}
