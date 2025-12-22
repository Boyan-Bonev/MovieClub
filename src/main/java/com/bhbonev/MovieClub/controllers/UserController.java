package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/register")
    public User registerUser(
            @RequestHeader String username,
            @RequestHeader String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
