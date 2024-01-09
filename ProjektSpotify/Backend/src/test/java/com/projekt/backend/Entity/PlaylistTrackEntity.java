package com.projekt.backend.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "playlist_track", schema = "SpotifyAppDB", catalog = "")
@IdClass(PlaylistTrackEntityPK.class)
public class PlaylistTrackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "playlist_id")
    private int playlistId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "track_id")
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
        PlaylistTrackEntity that = (PlaylistTrackEntity) o;
        return playlistId == that.playlistId && trackId == that.trackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, trackId);
    }
}
