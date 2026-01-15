package com.bhbonev.MovieClub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login-page";
    }

    @GetMapping("/logged")
    public String showLoggedPage() {
        return "logged";
    }
}
