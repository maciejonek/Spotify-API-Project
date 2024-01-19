package com.projekt.backend.Controller;

import com.projekt.backend.Entity.User;
import com.projekt.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/test")
    public int testGet(){
        return userService.getMappingTest();
    }
    @GetMapping("/get/{id}")
    public User getUserbyId(@RequestParam Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/all")
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
