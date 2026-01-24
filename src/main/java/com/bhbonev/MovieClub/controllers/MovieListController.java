package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.dtos.MovieListDto;
import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.services.MovieListService;
import com.bhbonev.MovieClub.services.MovieService;
import com.bhbonev.MovieClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @PostMapping("/lists/add")
    public String addList(Model model,
                          @ModelAttribute("movieListDto") MovieListDto movieListDto,
                          RedirectAttributes redirectAttributes) {
        String username = Objects.requireNonNull(model.getAttribute("username")).toString();

        try {
            User user = userService.loadUserByUsername(username);
            List<Movie> listedMovies = movieListDto.getMovieTitles().stream().map(title -> movieService.searchByTitle(title)).toList();
            if (movieListService.createList(user, movieListDto.getListName(), listedMovies) != null) {
                redirectAttributes.addFlashAttribute("message", "List successfully created!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Title and/or movie entries are missing!");
                redirectAttributes.addFlashAttribute("messageType", "fail");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "List creation failed!");
            redirectAttributes.addFlashAttribute("messageType", "fail");
        }

        return "redirect:/home";
    }
}
