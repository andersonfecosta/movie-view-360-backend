package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
import com.movieview360.movieview360.response.MovieCastingResponse;
import com.movieview360.movieview360.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieConverter {

    public Movie convertToMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setImgUrl(movieRequest.getImgUrl());
        //movie.setFavorite(movieRequest.isFavorite());

        MovieCategory movieCategory = new MovieCategory();
        movieCategory.setId(movieRequest.getGenderId());
        movie.setGender(movieCategory);

        return movie;
    }
}
