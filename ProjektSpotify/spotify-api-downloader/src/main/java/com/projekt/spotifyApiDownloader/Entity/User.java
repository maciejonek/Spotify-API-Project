package com.projekt.spotifyApiDownloader.Entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "spotify_id")
    private String spotifyId;
    @Column(name = "auth_token")
    private String authToken;
    @Column(name = "refresh_token")
    private String refreshToken;


    @OneToMany(mappedBy = "user")
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
