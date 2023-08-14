package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.MovieCastingRequest;
import org.springframework.stereotype.Component;

@Component
public class MovieCastingConverter {

    public MovieCasting convertToMovieCasting(MovieCastingRequest castingRequest, Movie movie, Casting casting) {
        return MovieCasting.builder()
                .movie(movie)
                .casting(casting)
                .role(castingRequest.getRole())
                .build();
    }

    public MovieCastingRequest convertToMovieCastingRequest(MovieCasting movieCasting) {
        return MovieCastingRequest.builder()
                .castingId(movieCasting.getCasting().getId())
                .movieId(movieCasting.getMovie().getId())
                .role(movieCasting.getRole())
                .build();
    }
}
