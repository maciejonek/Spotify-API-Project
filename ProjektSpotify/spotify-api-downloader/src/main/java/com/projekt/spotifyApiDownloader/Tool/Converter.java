package com.projekt.spotifyApiDownloader.Tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter<T> {
    private T object;

    public Converter(T object) {
        this.object = object;
    }

    public String convertEntityToString(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
