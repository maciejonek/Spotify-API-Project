package com.projekt.spotifyApiDownloader.Controller;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Service.PlaylistService;
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
    @GetMapping("/info/{id}")
    public Playlist getPlaylist(@PathVariable Long id){
        return playlistService.getPlaylist(id);
    }

    @DeleteMapping ("/remove")
    public void removeTrack(@RequestParam String playlist, @RequestParam String track, @RequestParam String token){
        playlistService.removeTrackFromPlaylist(playlist, track, token);
    }

    @PostMapping("/add")
    public void addTrack(@RequestParam String playlist, @RequestParam String track, @RequestParam String token){
        playlistService.addTrackFromPlaylist(playlist, track, token);
    }

    @PutMapping("/edit")
    public void editPlaylistDetails(@RequestBody Playlist playlist, @RequestParam String token){
        playlistService.editPlaylistDetails(playlist,token);
    }

}
