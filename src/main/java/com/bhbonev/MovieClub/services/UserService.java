package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.dtos.UserDto;
import com.bhbonev.MovieClub.exceptions.InvalidCredentialsExceptions;
import com.bhbonev.MovieClub.exceptions.UsernameAlreadyExistsException;
import com.bhbonev.MovieClub.models.Authority;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.AuthorityRepository;
import com.bhbonev.MovieClub.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username taken!");
        }
        if (!User.isValidPassword(userDto.getPassword())) {
           throw new InvalidCredentialsExceptions("Password should be between 8 and 16 characters and contain at least one uppercase, lowercase letter and a digit.");
        }
        if (!User.isValidUsername(userDto.getUsername())) {
            throw new InvalidCredentialsExceptions("Username should be between 4 and 16 characters and contain only letters, digits, . or _.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(new User(userDto));

        Authority authority = new Authority(userDto.getAuthority(), savedUser);
        authorityRepository.save(authority);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}