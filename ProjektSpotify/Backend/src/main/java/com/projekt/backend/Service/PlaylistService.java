package com.projekt.backend.Service;

import com.projekt.backend.Entity.Playlist;
import com.projekt.backend.Entity.Track;
import com.projekt.backend.Entity.User;
import com.projekt.backend.Exceptions.PlaylistNotFoundException;
import com.projekt.backend.Exceptions.TrackNotFoundException;
import com.projekt.backend.Exceptions.UserNotFoundException;
import com.projekt.backend.Repository.PlaylistRepository;
import com.projekt.backend.Repository.TrackRepository;
import com.projekt.backend.Repository.UserRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

    public static RestClient restClient;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, TrackRepository trackRepository) {
        PlaylistService.playlistRepository = playlistRepository;
        PlaylistService.userRepository = userRepository;
        PlaylistService.trackRepository = trackRepository;
        PlaylistService.restClient = RestClient.create();
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
        else throw new PlaylistNotFoundException("Playlist does not exist");
    }

    public void removeTrackFromPlaylist(Long playlistId, Long trackId, String trackUri, Long userId){
        if(playlistRepository.findById(playlistId).isPresent()){
            Playlist playlist = playlistRepository.findById(playlistId).get();
            List<Track> tracks = playlist.getPlaylistTracks().stream().toList();
            tracks.forEach(track -> {if (Objects.equals(track.getId(), trackId))
                playlist.getPlaylistTracks().remove(track);
            });
            playlistRepository.save(playlist);
            trackEditRequest(userId,trackUri, playlist, HttpMethod.DELETE,"remove");
        }
        else throw new PlaylistNotFoundException("Playlist does not exist");
    }

    public void addTrackToPlaylist(Long playlistId, Long trackId, String trackUri, Long userId){
        if(playlistRepository.findById(playlistId).isPresent()){
            if(trackRepository.findById(trackId).isPresent()){
                Playlist playlist = playlistRepository.findById(playlistId).get();
                Track track = trackRepository.findById(trackId).get();
                playlist.getPlaylistTracks().add(track);
                playlistRepository.save(playlist);
                trackEditRequest(userId,trackUri,playlist,HttpMethod.POST,"add");
            }
            else throw new TrackNotFoundException("Track does not exist");
        }
        else throw new PlaylistNotFoundException("Playlist does not exist");
    }

    public void editPlaylistDetails(Playlist playlistFrontend, Long userId){
        if(playlistRepository.findById(playlistFrontend.getDatabaseId()).isPresent()){
            Playlist playlist = playlistRepository.findById(playlistFrontend.getDatabaseId()).get();
            playlist.setName(playlistFrontend.getName());
            System.out.println(playlistFrontend.getDescription());
            playlist.setDescription(playlistFrontend.getDescription());
            playlist.setPublic(playlistFrontend.getPublic());
            playlistRepository.save(playlist);
            editDetailsRequest(playlist,userId);
        }
        else throw new PlaylistNotFoundException("Playlist does not exist");
    }



    private static void trackEditRequest(Long userId, String trackUri, Playlist playlist, HttpMethod method, String operation){
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            URI uri = URI.create("http://localhost:8080/playlist/"+operation+"?playlist=" + playlist.getPlaylistId() + "&track=" + trackUri + "&token=" + user.getAuthToken());
            restClient.method(method).uri(uri).retrieve();
        }
        else throw new UserNotFoundException("User does not exist");
    }
    private static void editDetailsRequest(Playlist playlist, Long userId){
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            URI uri = URI.create("http://localhost:8080/playlist/edit?token=" + user.getAuthToken());
            restClient.put()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(playlist)
                    .retrieve();
        }
        else throw new UserNotFoundException("User does not exist");
    }
}