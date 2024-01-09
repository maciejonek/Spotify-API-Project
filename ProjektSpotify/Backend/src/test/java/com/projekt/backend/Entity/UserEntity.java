package com.projekt.backend.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "User", schema = "SpotifyAppDB", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "display_name")
    private String displayName;
    @Basic
    @Column(name = "spotify_id")
    private String spotifyId;
    @Basic
    @Column(name = "auth_token")
    private String authToken;
    @Basic
    @Column(name = "refresh_token")
    private String refreshToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(displayName, that.displayName) && Objects.equals(spotifyId, that.spotifyId) && Objects.equals(authToken, that.authToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, spotifyId, authToken, refreshToken);
    }
}
