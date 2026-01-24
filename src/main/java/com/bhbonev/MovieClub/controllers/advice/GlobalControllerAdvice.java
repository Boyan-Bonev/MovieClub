package com.bhbonev.MovieClub.controllers.advice;

import com.bhbonev.MovieClub.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addGlobalAttributes(Model model,
                                    @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", user.getUsername());
        } else {
            model.addAttribute("isLoggedIn", false);
        }
    }
}
