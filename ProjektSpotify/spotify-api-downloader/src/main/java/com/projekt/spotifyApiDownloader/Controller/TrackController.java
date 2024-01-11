package com.projekt.spotifyApiDownloader.Controller;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.FromJsonObjects.UserPlaylist;
import com.projekt.spotifyApiDownloader.Service.PlaylistService;
import com.projekt.spotifyApiDownloader.Service.TrackService;
import org.json.JSONObject;
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
    @PostMapping("/download")
    public void addTracksFromPlaylist(@RequestBody UserPlaylist userPlaylist){
        User user = userPlaylist.getUser();
        System.out.println(user.getDisplayName());
        System.out.println(user.getAuthToken());
        System.out.println(user.getSpotifyId());
        Playlist playlist = userPlaylist.getPlaylist();
        System.out.println(playlist.getPlaylistId());
        System.out.println(playlist.getName());
        System.out.println(playlist.getDescription());
        trackService.addTracksFromPlaylist(playlist,user);
    }
}
