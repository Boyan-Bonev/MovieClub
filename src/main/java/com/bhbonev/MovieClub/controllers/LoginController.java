package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/logged";
        } else {
            return "login-page";
        }
    }

    @GetMapping("/logged")
    public String showLoggedPage() {
        return "logged";
    }
}
