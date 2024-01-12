package com.projekt.spotifyApiDownloader.Controller;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.FromJsonObjects.UserPlaylist;
import com.projekt.spotifyApiDownloader.Service.TrackService;
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
        trackService.addTracksFromPlaylist(user);
    }
    @GetMapping("/info")
    public Track getTrack(){
        return trackService.getTrack();
    }
}
