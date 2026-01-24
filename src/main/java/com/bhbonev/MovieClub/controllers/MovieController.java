package com.bhbonev.MovieClub.controllers;

import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/search")
    public List<String> searchMovies(@RequestParam String term) {
        return movieService.searchByTerm(term)
                .stream()
                .map(Movie::getTitle)
                .toList();
    }


}
