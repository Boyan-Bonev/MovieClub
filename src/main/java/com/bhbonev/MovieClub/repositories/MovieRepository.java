package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
