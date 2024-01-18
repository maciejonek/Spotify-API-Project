package com.projekt.spotifyApiDownloader.Tool;

import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class RequestSender {
    public static JSONObject sendRequest(HttpRequest request){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(!Objects.equals(response.body(), "")) return new JSONObject(response.body());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
