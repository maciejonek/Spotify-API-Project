package com.projekt.spotifyApiDownloader.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekt.spotifyApiDownloader.Entity.Track;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackListDTO {
    @JsonProperty("track")
    private Track track;

    public TrackListDTO() {
    }

    public TrackListDTO(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
