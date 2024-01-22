package com.projekt.backend.Service;

import com.projekt.backend.Entity.Playlist;
import com.projekt.backend.Entity.Track;
import com.projekt.backend.Entity.User;
import com.projekt.backend.Repository.PlaylistRepository;
import com.projekt.backend.Repository.TrackRepository;
import com.projekt.backend.Repository.UserRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class PlaylistService {
    public static PlaylistRepository playlistRepository;
    public static UserRepository userRepository;

    public static TrackRepository trackRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, TrackRepository trackRepository) {
        PlaylistService.playlistRepository = playlistRepository;
        PlaylistService.userRepository = userRepository;
        PlaylistService.trackRepository = trackRepository;
    }

    public Iterable<Playlist> getUsersPlaylists(Long id){
        if(playlistRepository.findByUser_Id(id).spliterator().getExactSizeIfKnown() != 0){
            return playlistRepository.findByUser_Id(id);
        }
        return null;
    }

    public Playlist getPlaylist(Long id){
        if(playlistRepository.findById(id).isPresent()){
            return playlistRepository.findById(id).get();
        }
        return null;
    }

    public void removeTrackFromPlaylist(Long playlistId, Long trackId, String trackUri, Long userId){
        if(playlistRepository.findById(playlistId).isPresent()){
            Playlist playlist = playlistRepository.findById(playlistId).get();
            List<Track> tracks = playlist.getPlaylistTracks().stream().toList();
            tracks.forEach(track -> {if (Objects.equals(track.getId(), trackId))
            playlist.getPlaylistTracks().remove(track);
            });
            playlistRepository.save(playlist);
            sendRequestToLocalApi(userId,trackUri, playlist, HttpMethod.DELETE,"remove");

        }
    }

    public void addTrackToPlaylist(Long playlistId, Long trackId, String trackUri, Long userId){
        if(playlistRepository.findById(playlistId).isPresent()){
            if(trackRepository.findById(trackId).isPresent()){
                Playlist playlist = playlistRepository.findById(playlistId).get();
                Track track = trackRepository.findById(trackId).get();
                playlist.getPlaylistTracks().add(track);
                playlistRepository.save(playlist);
                sendRequestToLocalApi(userId,trackUri,playlist,HttpMethod.POST,"add");
            }

        }
    }

    private static void sendRequestToLocalApi(Long userId, String trackUri, Playlist playlist, HttpMethod method, String operation){
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            RestClient restClient = RestClient.create();
            URI uri = URI.create("http://localhost:8080/playlist/"+operation+"?playlist=" + playlist.getPlaylistId() + "&track=" + trackUri + "&token=" + user.getAuthToken());
            restClient.method(method).uri(uri).retrieve();
        }
    }
}