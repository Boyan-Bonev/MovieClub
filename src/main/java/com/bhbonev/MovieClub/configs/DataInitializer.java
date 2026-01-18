package com.bhbonev.MovieClub.configs;

import com.bhbonev.MovieClub.dtos.UserDto;
import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.MovieListRepository;
import com.bhbonev.MovieClub.repositories.MovieRepository;
import com.bhbonev.MovieClub.repositories.UserRepository;
import com.bhbonev.MovieClub.services.MovieListService;
import com.bhbonev.MovieClub.services.TmdbService;
import com.bhbonev.MovieClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private MovieListService movieListService;

    @Value("${movieclub.admin.password}")
    private String password;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   MovieRepository movieRepository,
                                   MovieListRepository movieListRepository) {
        return args -> {
            User user = null ;
            List<Movie> movies = null;

            if (userRepository.count() == 0) {
                user = userService.registerUser(
                        new UserDto("admin", password, "ROLE_ADMIN"));
                userService.registerUser(
                        new UserDto("user", password, null));
            }

            if (movieRepository.count() == 0) {
                movies = tmdbService.importPopularMovies();
            }

            if (movieListRepository.count() == 0) {
                movieListService.createList(user, "Popular movies", movies);
            }
        };
    }
}
