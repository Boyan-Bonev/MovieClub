package com.bhbonev.MovieClub.configs;

import com.bhbonev.MovieClub.dtos.UserDto;
import com.bhbonev.MovieClub.repositories.UserRepository;
import com.bhbonev.MovieClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private UserService userService;

    @Value("${movieclub.admin.password}")
    private String password;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userService.registerUser(
                        new UserDto("admin", password, "ROLE_ADMIN"));
                userService.registerUser(
                        new UserDto("user", password, null));
            }
        };
    }
}
