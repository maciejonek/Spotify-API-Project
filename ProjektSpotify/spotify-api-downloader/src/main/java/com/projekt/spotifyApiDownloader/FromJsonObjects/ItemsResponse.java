package com.projekt.spotifyApiDownloader.FromJsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekt.spotifyApiDownloader.Entity.Track;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsResponse {
    @JsonProperty("items")
    private List<TracksItem> items;

    public List<TracksItem> getItems() {
        return items;
    }

    public void setItems(List<TracksItem> items) {
        this.items = items;
    }

    public List<Track> getTracks() {
        return items.stream().map(TracksItem::getTrack).toList();
    }
}
