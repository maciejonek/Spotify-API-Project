package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Service.PlaylistService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {
    public PlaylistService playlistService;
    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/download")
    public void getNewestPlaylist(@RequestBody User user){
        playlistService.getNewestPlaylist(user);
    }
}
