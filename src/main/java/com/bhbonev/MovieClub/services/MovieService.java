package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    public List<Movie> searchByTerm(String term) {
        return movieRepository.findByTitleContainingIgnoreCase(term);
    }
}
