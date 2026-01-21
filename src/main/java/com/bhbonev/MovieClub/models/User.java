package com.bhbonev.MovieClub.models;

import com.bhbonev.MovieClub.dtos.UserDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true, nullable = false)
    private String username;

    @Column(length = 256, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MovieList> movieLists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "userFollows",
            joinColumns = @JoinColumn(name = "followerId"),
            inverseJoinColumns = @JoinColumn(name = "followeeId")
    )
    private Set<User> followees = new HashSet<>(); // who follows the user

    @ManyToMany(mappedBy = "followees")
    private Set<User> followers = new HashSet<>(); // who the user follows

    @ManyToMany
    @JoinTable(
            name = "userLikedReviews",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "reviewId")
    )
    private Set<Review> likedReviews = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_liked_lists",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "listId")
    )
    private Set<MovieList> likedLists = new HashSet<>();

    @Transient
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";
    @Transient
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9._]{2,14}[a-zA-Z0-9]$";


    public User(UserDto userDto) {
        this.setUsername(userDto.getUsername());
        this.setPassword(userDto.getPassword());
    }

    public User() {

    }

    public Set<Review> getLikedReviews() {
        return likedReviews;
    }

    public void setLikedReviews(Set<Review> likedReviews) {
        this.likedReviews = likedReviews;
    }

    public Set<MovieList> getLikedLists() {
        return likedLists;
    }

    public void setLikedLists(Set<MovieList> likedLists) {
        this.likedLists = likedLists;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<MovieList> getLists() {
        return movieLists;
    }

    public void setLists(Set<MovieList> movieLists) {
        this.movieLists = movieLists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_PATTERN, username);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }
}