package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.PlaylistRepository;
import com.projekt.spotifyApiDownloader.Repository.TrackRepository;
import com.projekt.spotifyApiDownloader.Tool.TrackDownloader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TrackService {
    public TrackRepository trackRepository;
    public PlaylistRepository playlistRepository;

    public TrackService(TrackRepository trackRepository, PlaylistRepository playlistRepository) {
        this.trackRepository = trackRepository;
        this.playlistRepository = playlistRepository;
    }

    public void addTracksFromPlaylist(User user){
        List<Playlist> playlists = new ArrayList<>(user.getUserPlaylists());
        for(Playlist playlist : playlists) {
            List<Track> tracks = TrackDownloader.getTracksFromPlaylist(playlist, user);
            for (Track track : tracks) {
                Track existingTrack = trackRepository.findByTrackId(track.getTrackId());
                Playlist existingPlaylist = playlistRepository.findByPlaylistId(playlist.getPlaylistId());
                if (existingTrack == null) {
                    System.out.println("new track");
                    track.getPlaylists().add(existingPlaylist);
                    existingPlaylist.getPlaylistTracks().add(track);
                    trackRepository.save(track);
                } else {
                    System.out.println("track exists");
                    existingTrack.getPlaylists().add(existingPlaylist);
                    existingPlaylist.getPlaylistTracks().add(existingTrack);
                    trackRepository.save(existingTrack);
                    playlistRepository.save(existingPlaylist);
                }
            }
        }

    }
    public Track getTrack(){
        return trackRepository.findById(5L).get();
    }


}

//        tracks.forEach(track -> {
//            if(trackRepository.findByTrackId(track.getTrackId())==null){
//                System.out.println("new track");
//                tracks
//                trackRepository.save(track);
//            }
//            else{
//                System.out.println("track exists");
//                Track originTrack = trackRepository.findByTrackId(track.getTrackId());
//                originTrack.getPlaylists().add(playlist);
//                trackRepository.save(originTrack);
//            }
//        });