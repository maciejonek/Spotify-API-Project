package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.DTO.PlaylistDetailsDTO;
import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.PlaylistRepository;
import com.projekt.spotifyApiDownloader.Repository.UserRepository;
import com.projekt.spotifyApiDownloader.Downloader.PlaylistDownloader;
import com.projekt.spotifyApiDownloader.Updater.PlaylistDetailsUpdater;
import com.projekt.spotifyApiDownloader.Updater.PlaylistItemsUpdater;
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
                originPlaylist.setPublic(playlist.getPublic());
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

    public void removeTrackFromPlaylist(String playlist, String track, String token){
        PlaylistItemsUpdater updater = new PlaylistItemsUpdater();
        updater.removePlaylistItem(playlist,token, track);
    }

    public void addTrackFromPlaylist(String playlist, String track, String token){
        PlaylistItemsUpdater updater = new PlaylistItemsUpdater();
        updater.addPlaylistItem(playlist,token,track);
    }

    public void editPlaylistDetails(Playlist playlist,String token){
        PlaylistDetailsUpdater updater = new PlaylistDetailsUpdater();
        updater.editDetails(playlist,token);
    }
}
