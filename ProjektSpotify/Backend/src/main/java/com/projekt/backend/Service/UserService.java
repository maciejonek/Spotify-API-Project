package com.projekt.backend.Service;

import com.projekt.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public int getMappingTest(){
        return 1;
    }


}
