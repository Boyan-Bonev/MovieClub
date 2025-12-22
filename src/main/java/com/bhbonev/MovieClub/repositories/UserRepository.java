package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
