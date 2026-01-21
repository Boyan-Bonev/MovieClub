package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.Follower;
import org.springframework.data.repository.CrudRepository;

public interface FollowerRepository extends CrudRepository<Follower, Long> {
}
