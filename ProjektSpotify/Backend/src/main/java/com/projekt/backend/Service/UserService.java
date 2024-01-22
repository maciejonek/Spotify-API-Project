package com.projekt.backend.Service;

import com.projekt.backend.Entity.User;
import com.projekt.backend.Exceptions.UserNotFoundException;
import com.projekt.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        else
            throw new UserNotFoundException("User does not exist");
    }


}
