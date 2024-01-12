package com.projekt.spotifyApiDownloader.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlist")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long databaseId;
    @JsonProperty("playlistId")
    @JsonAlias("id")
    @Column(name = "playlist_id")
    private String playlistId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private Set<Track> playlistTracks = new HashSet<>();

    public Playlist() {
    }
        public Playlist(String playlistId, String name, String description) {
        this.playlistId = playlistId;
        this.name = name;
        this.description = description;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    @JsonProperty("id")
    public String getPlaylistId() {
        return playlistId;
    }
    @JsonProperty("id")
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Track> getPlaylistTracks() {
        return playlistTracks;
    }

    public void setPlaylistTracks(Set<Track> playlistTracks) {
        this.playlistTracks = playlistTracks;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
