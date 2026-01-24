package com.bhbonev.MovieClub.services;

import com.bhbonev.MovieClub.dtos.ReviewDto;
import com.bhbonev.MovieClub.models.Movie;
import com.bhbonev.MovieClub.models.Review;
import com.bhbonev.MovieClub.models.User;
import com.bhbonev.MovieClub.repositories.MovieRepository;
import com.bhbonev.MovieClub.repositories.ReviewRepository;
import com.bhbonev.MovieClub.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Review createReview(String username, ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        Movie movie = movieRepository.findByTitle(reviewDto.getMovieTitle()).orElse(null);
        if (movie == null) {
            return null;
        }

        Review review = new Review();
        review.setUser(user);
        review.setReviewDate(LocalDate.now());
        review.setReviewText(reviewDto.getReviewText());
        review.setMovie(movie);
        review.setRating(reviewDto.getRating());
        return reviewRepository.save(review);
    }
}
