package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.services.CastingService;
import com.movieview360.movieview360.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieCastingConverter {
    @Autowired
    private MovieService movieService;

    @Autowired
    private CastingService castingService;

    public MovieCasting convertToMovieCasting(MovieCastingRequest castingRequest) {
        MovieCasting movieCasting = new MovieCasting();

        Movie movie = movieService.getMovieById(castingRequest.getMovieId());
        Casting casting = castingService.getCastingById(castingRequest.getCastingId());

        movieCasting.setMovie(movie);
        movieCasting.setCasting(casting);
        movieCasting.setRole(castingRequest.getRole());

        return movieCasting;
    }


}
