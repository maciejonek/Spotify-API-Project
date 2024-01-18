package com.projekt.spotifyApiDownloader.DTO;

import com.projekt.spotifyApiDownloader.Entity.Playlist;
import com.projekt.spotifyApiDownloader.Entity.User;

public class UserDTO {
    private User user;
//    private Playlist playlist;

    public UserDTO(User user, Playlist playlist) {
        this.user = user;
//        this.playlist = playlist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Playlist getPlaylist() {
//        return playlist;
//    }

//    public void setPlaylist(Playlist playlist) {
//        this.playlist = playlist;
//    }
}