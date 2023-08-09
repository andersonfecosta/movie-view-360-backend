package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.MovieCastingRequest;
import org.springframework.stereotype.Component;

@Component
public class MovieCastingConverter {

    public MovieCasting convertToMovieCasting(MovieCastingRequest castingRequest) {
        MovieCasting movieCasting = new MovieCasting();

        Movie movie = new Movie();
        movie.setId(castingRequest.getMovieId());

        Casting casting = new Casting();
        casting.setId(castingRequest.getCastingId());

        movieCasting.setMovie(movie);
        movieCasting.setCasting(casting);
        movieCasting.setRole(castingRequest.getRole());

        return movieCasting;
    }


}
