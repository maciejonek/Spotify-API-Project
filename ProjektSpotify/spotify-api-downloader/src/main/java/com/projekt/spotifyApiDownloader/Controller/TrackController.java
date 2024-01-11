package com.projekt.spotifyApiDownloader.Controller;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.FromJsonObjects.UserPlaylist;
import com.projekt.spotifyApiDownloader.Service.PlaylistService;
import com.projekt.spotifyApiDownloader.Service.TrackService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/track")
public class TrackController {
    public TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("/download")
    public void addTracksFromPlaylist(@RequestBody UserPlaylist userPlaylist){
        User user = userPlaylist.getUser();
        Playlist playlist = userPlaylist.getPlaylist();
        trackService.addTracksFromPlaylist(playlist,user);
    }
    @GetMapping("/info")
    public Track getTrack(){
        return trackService.getTrack();
    }
}
