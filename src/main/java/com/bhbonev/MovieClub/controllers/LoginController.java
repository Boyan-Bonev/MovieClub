package com.bhbonev.MovieClub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        if ((boolean) model.getAttribute("isLoggedIn")) {
            return "redirect:/home";
        } else {
            return "login-page";
        }
    }

    @GetMapping("/home")
    public String showLoggedPage() {
        return "home";
    }
}
