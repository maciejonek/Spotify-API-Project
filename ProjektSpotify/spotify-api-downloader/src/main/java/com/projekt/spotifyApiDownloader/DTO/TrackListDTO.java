package com.projekt.spotifyApiDownloader.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackListDTO {
    @JsonProperty("track")
    private TrackDTO trackDTO;
    public TrackListDTO() {
    }

    public TrackListDTO(TrackDTO trackDTO) {
        this.trackDTO = trackDTO;
    }

    public TrackDTO getTrackDTO() {
        return trackDTO;
    }

    public void setTrackDTO(TrackDTO trackDTO) {
        this.trackDTO = trackDTO;
    }

}
