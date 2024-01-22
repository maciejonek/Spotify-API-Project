package com.projekt.backend.Exceptions;

public class PlaylistNotFoundException extends RuntimeException{
    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
