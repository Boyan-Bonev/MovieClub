package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.dtos.ReviewDto;
import com.bhbonev.MovieClub.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews/add")
    public String addReview (Model model,
                           @ModelAttribute("review") ReviewDto reviewDto,
                           RedirectAttributes redirectAttributes) {
        try {
            String username = Objects.requireNonNull(model.getAttribute("username")).toString();
            if (reviewService.createReview(username, reviewDto) != null) {
                redirectAttributes.addFlashAttribute("message", "Review successfully created!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Review creation failed!");
                redirectAttributes.addFlashAttribute("messageType", "fail");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "You are not logged in!");
            redirectAttributes.addFlashAttribute("messageType", "fail");
        }

        return "redirect:/home";
    }
}
