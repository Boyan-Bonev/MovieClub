package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.MovieList;
import org.springframework.data.repository.CrudRepository;

public interface MovieListRepository extends CrudRepository<MovieList, Long> {
}
