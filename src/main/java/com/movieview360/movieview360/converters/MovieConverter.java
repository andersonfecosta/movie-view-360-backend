package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieRequest;
import org.springframework.stereotype.Component;

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
        movie.setGender(movieCategory);

        return movie;
    }


}
