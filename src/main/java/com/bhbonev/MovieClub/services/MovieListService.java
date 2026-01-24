package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.models.MovieList;
import com.bhbonev.MovieClub.models.MovieListEntry;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.MovieListEntryRepository;
import com.bhbonev.MovieClub.repositories.MovieListRepository;
import com.bhbonev.MovieClub.repositories.MovieRepository;
import com.bhbonev.MovieClub.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListService {

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private MovieListEntryRepository movieListEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public MovieList createList(String username, String name, List<String> movies) {
        if (username == null || name == null  || movies == null ||
                name.isBlank() || username.isBlank()) {
            return null;
        }

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        MovieList movieList = new MovieList();
        movieList.setName(name);
        movieList.setUser(user);

        MovieList savedMovieList = movieListRepository.save(movieList);

        for (String movieTitle : movies) {
            Movie movie = movieRepository.findByTitle(movieTitle).orElse(null);

            if (movie == null) {
                continue;
            }

            MovieListEntry entry = new MovieListEntry();
            entry.setMovie(movie);
            entry.setMovieList(savedMovieList);
            movieListEntryRepository.save(entry);
        }

        return savedMovieList;
    }
}
