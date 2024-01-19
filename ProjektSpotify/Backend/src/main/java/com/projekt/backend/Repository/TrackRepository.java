package com.projekt.backend.Repository;

import com.projekt.backend.Entity.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track,Long> {
}
