package com.projekt.spotifyApiDownloader.Service;

import com.projekt.spotifyApiDownloader.Downloader.UserDownloader;
import com.projekt.spotifyApiDownloader.Entity.User;
import com.projekt.spotifyApiDownloader.Repository.UserRepository;
import com.projekt.spotifyApiDownloader.Tool.Authenticator;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public int getMappingTest() {
        return 1;
    }

    public String getSpotifyURL() {
        return Authenticator.generateAuthURL();
    }

    public User addConnectedUserToDb(String callback) {
        JSONObject tokenJson = Authenticator.getTokenJson(callback);
        User user = new User(tokenJson.getString("access_token"), tokenJson.getString("refresh_token"));

        UserDownloader downloader = new UserDownloader(user);
        JSONObject userJson = downloader.getUserJson();

        user.setDisplayName(userJson.getString("display_name"));
        user.setSpotifyId(userJson.getString("id"));

        if (userRepository.findBySpotifyId(user.getSpotifyId()) == null) {
            System.out.println("User does not exist, adding");
            userRepository.save(user);
            downloader.userJsonToPlaylistController();
            return user;

        } else{
            User originUser = userRepository.findBySpotifyId(user.getSpotifyId());
            System.out.println("User exists, refreshing tokens");
            originUser.setAuthToken(user.getAuthToken());
            originUser.setRefreshToken(user.getRefreshToken());
            userRepository.save(originUser);
            downloader.setUser(originUser);
            downloader.userJsonToPlaylistController();
            return originUser;
        }


    }
}