package com.projekt.backend.Controller;

import com.projekt.backend.Entity.Track;
import com.projekt.backend.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/all")
    public Iterable<Track> getAllTracks(){
        return trackService.getAllTracks();
    }
}
