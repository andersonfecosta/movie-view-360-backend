package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.repositories.CastingRepository;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.response.MovieCastingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class MovieCastingConverter {

    public MovieCasting convertToMovieCasting(MovieCastingRequest castingRequest, Movie movie, Casting casting) {
        MovieCasting movieCasting = new MovieCasting();
        movieCasting.setMovie(movie);
        movieCasting.setCasting(casting);
        movieCasting.setRole(castingRequest.getRole());
        return movieCasting;
    }
}
