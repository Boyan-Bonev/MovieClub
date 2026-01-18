package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.dtos.TmdbResponse;
import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.repositories.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TmdbService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Value("${movieclub.tmdb.api.key}")
    private String API_KEY;
    private String URL;

    @PostConstruct
    public void init() {
        URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;
    }

    public void importPopularMovies() {
        TmdbResponse response = restTemplate.getForObject(URL, TmdbResponse.class);

        if (response != null && response.getResults() != null) {
            List<Movie> movies = response.getResults().stream().map(dto -> {
                Movie movie = new Movie();
                movie.setTitle(dto.getTitle());
                movie.setOverview(dto.getOverview());
                movie.setReleaseDate(dto.getReleaseDate());
                return movie;
            }).collect(Collectors.toList());

            movieRepository.saveAll(movies);
        }
    }
}
