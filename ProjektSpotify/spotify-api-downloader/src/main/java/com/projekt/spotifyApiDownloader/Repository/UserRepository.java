package com.projekt.spotifyApiDownloader.Repository;

import com.projekt.spotifyApiDownloader.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findBySpotifyId(String spotifyId);
}
