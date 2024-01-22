package com.projekt.backend.Exceptions;

public class TrackNotFoundException extends RuntimeException{
    public TrackNotFoundException(String message) {
        super(message);
    }
}
