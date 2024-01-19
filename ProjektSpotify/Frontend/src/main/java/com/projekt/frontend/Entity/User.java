package com.projekt.frontend.Entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String displayName;
    private String spotifyId;
    private String authToken;
    private String refreshToken;


    private Set<Playlist> userPlaylists = new HashSet<>();

    public User() {
    }

    public User(String authToken, String refreshToken) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public Set<Playlist> getUserPlaylists() {
        return userPlaylists;
    }

    public void setUserPlaylists(Set<Playlist> userPlaylists) {
        this.userPlaylists = userPlaylists;
    }
}
