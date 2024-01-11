package com.projekt.spotifyApiDownloader.FromJsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekt.spotifyApiDownloader.Entity.Track;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TracksItem{
    @JsonProperty("track")
    private Track track;

    public TracksItem() {
    }

    public TracksItem(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
