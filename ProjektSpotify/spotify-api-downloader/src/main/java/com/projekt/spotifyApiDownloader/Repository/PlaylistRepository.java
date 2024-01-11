package com.projekt.spotifyApiDownloader.Repository;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Tool.PlaylistDownloader;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist,Long> {
    Playlist findByPlaylistId(String playlistId);
}
