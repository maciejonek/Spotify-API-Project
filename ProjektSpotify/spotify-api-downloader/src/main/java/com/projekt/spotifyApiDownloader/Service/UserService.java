package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.UserRepository;
import com.projekt.spotifyApiDownloader.Tool.UserAuthenticator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }
    public int getMappingTest(){
        return 1;
    }

    public String getSpotifyURL(){
        return UserAuthenticator.generateAuthURL();
    }
    public void addConnectedUserToDb(String callback){
        JSONObject tokenJson = UserAuthenticator.getTokenJson(callback);
        User user = new User(tokenJson.getString("access_token"),tokenJson.getString("refresh_token"));
        JSONObject userJson = UserAuthenticator.getUserJson(user);
        user.setDisplayName(userJson.getString("display_name"));
        user.setSpotifyId(userJson.getString("id"));
        if(userRepository.findBySpotifyId(user.getSpotifyId()) == null)
        {
            System.out.println("User does not exist, adding");
            userRepository.save(user);
        }
        else
            System.out.println("User exists");

    }



}
