package com.bhbonev.MovieClub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MovieClubApplication {

	static void main(String[] args) {
		SpringApplication.run(MovieClubApplication.class, args);
	}

}
