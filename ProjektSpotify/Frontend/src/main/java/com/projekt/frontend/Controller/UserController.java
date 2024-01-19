package com.projekt.frontend.Controller;

import com.projekt.frontend.Entity.User;
import com.projekt.frontend.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class UserController {
    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getAllUsers(Model model, HttpSession session){
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("url",userService.SpotifyURL());
        String sharedVariable = (String) session.getAttribute("sharedVariable");
        model.addAttribute("msg", sharedVariable);
        return "index";
    }

}
