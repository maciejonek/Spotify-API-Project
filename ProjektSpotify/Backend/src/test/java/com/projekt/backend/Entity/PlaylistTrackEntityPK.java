package com.projekt.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistTrackEntityPK implements Serializable {
    @Column(name = "playlist_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playlistId;
    @Column(name = "track_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trackId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrackEntityPK that = (PlaylistTrackEntityPK) o;
        return playlistId == that.playlistId && trackId == that.trackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, trackId);
    }
}
