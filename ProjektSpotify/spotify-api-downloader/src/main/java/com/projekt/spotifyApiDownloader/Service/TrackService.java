package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.Track;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.TrackRepository;
import com.projekt.spotifyApiDownloader.Tool.TrackDownloader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TrackService {
    public TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Transactional
    public void addTracksFromPlaylist(Playlist playlist, User user){
        List<Track> tracks = TrackDownloader.getTracksFromPlaylist(playlist,user);
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
        for (Track track : tracks) {
            Track existingTrack = trackRepository.findByTrackId(track.getTrackId());

            if (existingTrack == null) {
                // Nowy utwór
                System.out.println("new track");
                track.getPlaylists().add(playlist);
                trackRepository.save(track);
            } else {
                // Utwór już istnieje
                System.out.println("track exists");
                existingTrack.getPlaylists().add(playlist);
                trackRepository.save(existingTrack);
            }
        }

    }
    public Track getTrack(){
        return trackRepository.findById(1L).get();
    }


}