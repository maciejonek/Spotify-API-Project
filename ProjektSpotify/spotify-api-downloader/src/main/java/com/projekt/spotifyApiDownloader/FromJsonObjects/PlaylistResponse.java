package com.projekt.spotifyApiDownloader.FromJsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekt.spotifyApiDownloader.Entity.Playlist;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistResponse {
    @JsonProperty("items")
    private List<Playlist> playlists;

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
