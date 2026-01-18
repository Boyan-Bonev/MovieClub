package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
