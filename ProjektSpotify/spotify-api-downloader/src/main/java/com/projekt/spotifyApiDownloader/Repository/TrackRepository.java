package com.projekt.spotifyApiDownloader.Repository;

import com.projekt.spotifyApiDownloader.Entity.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track,Long> {
}
