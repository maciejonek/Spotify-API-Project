package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Repository.TrackRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    public TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void addTracksFromPlaylist(Playlist playlist){
        System.out.println(playlist);
        System.out.println(playlist);
    }


}
