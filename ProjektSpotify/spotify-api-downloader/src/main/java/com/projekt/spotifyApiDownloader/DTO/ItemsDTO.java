package com.projekt.spotifyApiDownloader.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekt.spotifyApiDownloader.Entity.Track;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsDTO {
    @JsonProperty("items")
    private List<TrackListDTO> items;


    public List<TrackListDTO> getItems() {
        return items;
    }

    public void setItems(List<TrackListDTO> items) {
        this.items = items;
    }

}
