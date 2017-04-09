package com.kafilicious.popularmovies.Models;

/**
 * Created by Abdulkarim on 4/6/2017.
 */

import java.util.List;

public class MovieList {

    public String posterPath;
    public boolean adult;
    public String overview;
    public String releaseDate;
    public List<Long> genreIds = null;
    public long id;
    public String originalTitle;
    public String originalLanguage;
    public String title;
    public String backdropPath;
    public double popularity;
    public long voteCount;
    public boolean video;
    public double voteAverage;


}