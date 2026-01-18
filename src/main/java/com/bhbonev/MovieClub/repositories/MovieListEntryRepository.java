package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.MovieListEntry;
import org.springframework.data.repository.CrudRepository;

public interface MovieListEntryRepository extends CrudRepository<MovieListEntry, Long> {
}
