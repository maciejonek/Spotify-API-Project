package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/track")
public class TrackController {
    public TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("/from/playlist")
    public void addTracksFromPlaylist(@RequestBody Playlist playlist){
        System.out.println(1);
            trackService.addTracksFromPlaylist(playlist);
    }
}
