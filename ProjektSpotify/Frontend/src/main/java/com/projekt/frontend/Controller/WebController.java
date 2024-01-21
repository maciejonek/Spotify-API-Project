package com.projekt.frontend.Controller;

import com.projekt.frontend.Entity.Playlist;
import com.projekt.frontend.Entity.User;
import com.projekt.frontend.Service.PlaylistService;
import com.projekt.frontend.Service.TrackService;
import com.projekt.frontend.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class WebController {
    public UserService userService;
    public PlaylistService playlistService;
    public TrackService trackService;

    @Autowired
    public WebController(UserService userService, PlaylistService playlistService, TrackService trackService) {
        this.userService = userService;
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @GetMapping("/index")
    public String getIndexContent(Model model, HttpSession session){
        String userName = (String) session.getAttribute("userName");
        Long userId = (Long) session.getAttribute("userId");
        List<Playlist> playlists = new ArrayList<>();

        if(userId != null)
            if(playlistService.getPlaylists(userId) != null)
                playlists.addAll(playlistService.getPlaylists(userId));

        model.addAttribute("url",userService.SpotifyURL());
        model.addAttribute("tracks", trackService.getAllTracks());
        model.addAttribute("playlists", playlists);
        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId);

        return "index";
    }

    @GetMapping("/playlist/{id}")
    public String playlistInspect(@PathVariable Long id, Model model,HttpSession session){
        model.addAttribute("playlist", playlistService.getPlaylistInfo(id));
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        return "playlist";
    }
    @GetMapping("/playlist/{id}/delete/{track}")
    public String deleteTrack(@PathVariable Long id, @PathVariable Long track, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if(!Objects.equals(userId, playlistService.getPlaylistInfo(id).getUserId())) return "redirect:/index";
        playlistService.deleteTrack(id,track);
        return "redirect:/playlist/"+id;
    }


}
