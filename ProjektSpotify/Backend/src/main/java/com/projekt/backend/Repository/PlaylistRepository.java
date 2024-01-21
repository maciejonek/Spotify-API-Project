package com.projekt.backend.Repository;

import com.projekt.backend.Entity.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist,Long> {
        Iterable<Playlist> findByUser_Id(Long id);
}
