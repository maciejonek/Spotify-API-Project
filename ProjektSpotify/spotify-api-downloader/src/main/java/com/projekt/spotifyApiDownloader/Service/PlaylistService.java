package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.PlaylistRepository;
import com.projekt.spotifyApiDownloader.Repository.UserRepository;
import com.projekt.spotifyApiDownloader.Tool.PlaylistDownloader;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    public PlaylistRepository playlistRepository;
    public UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public void getNewestPlaylist(){
        User user = userRepository.findById(1L).get();
        PlaylistDownloader.getSpotifyPlaylist(user);

    }
}
