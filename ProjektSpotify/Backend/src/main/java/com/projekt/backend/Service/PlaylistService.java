package com.projekt.backend.Service;

import com.projekt.backend.Entity.Playlist;
import com.projekt.backend.Entity.Track;
import com.projekt.backend.Repository.PlaylistRepository;
import com.projekt.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    public PlaylistRepository playlistRepository;
    public UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Iterable<Playlist> getUsersPlaylists(Long id){
        if(playlistRepository.findByUser_Id(id).spliterator().getExactSizeIfKnown() != 0){
            return playlistRepository.findByUser_Id(id);
        }
        return null;
    }

    public Playlist getPlaylist(Long id){
        if(playlistRepository.findById(id).isPresent()){
            Playlist playlist =  playlistRepository.findById(id).get();
            return playlist;
        }
        return null;
    }

    public void removeTrackFromPlaylist(Long playlistId, Long trackId){
        if(playlistRepository.findById(playlistId).isPresent()){
            Playlist playlist = playlistRepository.findById(playlistId).get();
            List<Track> tracks = playlist.getPlaylistTracks().stream().toList();
            tracks.forEach(track -> {if (track.getId()==trackId)
            playlist.getPlaylistTracks().remove(track);
            });
            playlistRepository.save(playlist);
        }
    }
}