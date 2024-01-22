package com.projekt.backend.Controller;


import com.projekt.backend.Entity.Playlist;
import com.projekt.backend.Service.PlaylistService;
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

    @GetMapping("/user/{id}")
    public Iterable<Playlist> getUsersPlaylists(@PathVariable Long id){
        return playlistService.getUsersPlaylists(id);
    }
    @GetMapping("/info/{id}")
    public Playlist getPlaylist(@PathVariable Long id){
        return playlistService.getPlaylist(id);
    }
    @DeleteMapping("/delete")
    public void removeTrackFromPlaylist(@RequestParam Long id, @RequestParam Long track,@RequestParam String uri, @RequestParam Long user){
        playlistService.removeTrackFromPlaylist(id,track,uri,user);
    }
    @PostMapping("/add")
    public void addTrackToPlaylist(@RequestParam Long id, @RequestParam Long track,@RequestParam String uri, @RequestParam Long user){
        playlistService.addTrackToPlaylist(id,track,uri,user);
    }
}
