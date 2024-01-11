package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.TrackRepository;
import com.projekt.spotifyApiDownloader.Tool.Converter;
import com.projekt.spotifyApiDownloader.Tool.TrackDownloader;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    public TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void addTracksFromPlaylist(Playlist playlist, User user){
        TrackDownloader.getTracksFromPlaylist(playlist,user);
    }


}
