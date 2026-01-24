package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.dtos.UserDto;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        if ((boolean) model.getAttribute("isLoggedIn")) {
            return "redirect:/home";
        } else {
            UserDto userDto = new UserDto();
            model.addAttribute("user", userDto);
            return "register-page";
        }
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("user") UserDto userDto,
            Model model) {
        try {
            userService.registerUser(userDto);
        } catch (Exception e) {
            model.addAttribute("registrationError", e.getMessage());
            return "register-page";
        }

        return "redirect:/login?success";
    }
}
