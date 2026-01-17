package com.bhbonev.MovieClub.repositories;

import com.bhbonev.MovieClub.models.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    List<GrantedAuthority> findByUserId(Long userId);
}
