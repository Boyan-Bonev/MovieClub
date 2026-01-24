package com.bhbonev.MovieClub.dtos;

import java.util.List;

public class MovieListDto {

    private String listName;
    private List<String> movieTitles;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<String> getMovieTitles() {
        return movieTitles;
    }

    public void setMovieTitles(List<String> movieTitles) {
        this.movieTitles = movieTitles;
    }
}
