package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.PlaylistRepository;
import com.projekt.spotifyApiDownloader.Repository.UserRepository;
import com.projekt.spotifyApiDownloader.Tool.PlaylistDownloader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    public PlaylistRepository playlistRepository;
    public UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public void getNewestPlaylist(User user){
        List<Playlist> playlists =  PlaylistDownloader.getSpotifyPlaylist(user);
        User originUser = userRepository.findBySpotifyId(user.getSpotifyId());
        playlists.forEach(playlist -> {
            if(playlistRepository.findByPlaylistId(playlist.getPlaylistId())==null) {
                System.out.println("new playlist");
                playlist.setUser(originUser);
                playlistRepository.save(playlist);
                originUser.getUserPlaylists().add(playlist);
                userRepository.save(originUser);
            }
            else{
                System.out.println("Playlist update");
                Playlist originPlaylist = playlistRepository.findByPlaylistId(playlist.getPlaylistId());
                originPlaylist.setDescription(playlist.getDescription());
                originPlaylist.setName(playlist.getName());
                originPlaylist.getPlaylistTracks().clear();
                originPlaylist.setUser(originUser);
                playlistRepository.save(originPlaylist);
                originUser.getUserPlaylists().add(originPlaylist);
                userRepository.save(originUser);
            }
        });
        PlaylistDownloader.addTracksToPlaylist(originUser);
    }
    public Playlist getPlaylist(Long id){
        return this.playlistRepository.findById(id).get();
    }
}
