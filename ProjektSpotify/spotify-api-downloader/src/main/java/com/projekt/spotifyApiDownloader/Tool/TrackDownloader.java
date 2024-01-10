package com.projekt.spotifyApiDownloader.Tool;

public class TrackDownloader {

    private static final String clientId = System.getenv("SPOTIFY_CLIENT");
    private static final String clientSecret = System.getenv("SPOTIFY_SECRET");
    private static final String redirectUri = "http://localhost:8080/user/callback";

}
