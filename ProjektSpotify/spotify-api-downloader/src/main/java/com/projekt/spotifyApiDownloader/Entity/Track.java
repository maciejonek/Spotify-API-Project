package com.projekt.spotifyApiDownloader.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "track")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "track_id")
    @JsonProperty("id")
    private String trackId;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "playlistTracks")
    private Set<Playlist> playlists = new HashSet<>();

    public Track() {}


    public Track(String trackId, String name, String image) {
        this.trackId = trackId;
        this.name = name;
        this.image = image;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getTrackId() {
        return trackId;
    }

    @JsonProperty("id")
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getImage() {
        return image;
    }
    @JsonProperty("type")
    public void setImage(String image) {
        this.image = image;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}
