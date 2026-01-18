package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.models.MovieList;
import com.bhbonev.MovieClub.models.MovieListEntry;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.MovieListEntryRepository;
import com.bhbonev.MovieClub.repositories.MovieListRepository;
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

    @Transactional
    public MovieList createList(User user, String name, List<Movie> movies) {
        if (user == null || movies == null) {
            return null;
        }

        MovieList movieList = new MovieList();
        movieList.setName(name);
        movieList.setUser(user);

        MovieList savedMovieList = movieListRepository.save(movieList);

        for (Movie movie : movies) {
            MovieListEntry entry = new MovieListEntry();
            entry.setMovie(movie);
            entry.setMovieList(savedMovieList);
            movieListEntryRepository.save(entry);
        }

        return savedMovieList;
    }
}
