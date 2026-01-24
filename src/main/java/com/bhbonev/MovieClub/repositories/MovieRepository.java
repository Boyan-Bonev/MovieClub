package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
