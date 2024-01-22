package com.projekt.backend.Service;

import com.projekt.backend.Entity.Track;
import com.projekt.backend.Repository.TrackRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    public TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Iterable<Track> getAllTracks(){
        return trackRepository.findAll();
    }
}
