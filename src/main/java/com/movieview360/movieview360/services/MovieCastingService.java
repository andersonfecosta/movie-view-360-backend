package com.movieview360.movieview360.services;


import com.movieview360.movieview360.converters.MovieCastingConverter;
import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.repositories.MovieCastingRepository;
import com.movieview360.movieview360.request.MovieCastingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCastingService {

    @Autowired
    private MovieCastingRepository movieCastingRepository;
    @Autowired
    private MovieCastingConverter movieCastingConverter;
    @Autowired
    private CastingService castingService;

    public List<MovieCasting> getAllMovieCastings() {
        return movieCastingRepository.findAll();
    }

    public MovieCasting getMovieCastingById(Long id) {
        return movieCastingRepository.findById(id).orElse(null);
    }

    public List<MovieCasting> createMovieCastings(List<MovieCastingRequest> castingRequests, Movie movie) {
        List<MovieCasting> movieCastings = new ArrayList<>();

        for (MovieCastingRequest castingRequest : castingRequests) {
            Casting casting = castingService.getCastingById(castingRequest.getCastingId());
            MovieCasting movieCasting = movieCastingConverter.convertToMovieCasting(castingRequest, movie, casting);

            movieCastings.add(movieCasting);

            movie.addCasting(movieCasting);

            casting.addMovieCasting(movieCasting);
        }
        return movieCastings;
    }

    public MovieCasting updateMovieCasting(Long id, MovieCastingRequest updatedMovieCasting) {
        MovieCasting existingMovieCasting = movieCastingRepository.findById(id).orElse(null);

        Casting casting = castingService.getCastingById(updatedMovieCasting.getCastingId());

        existingMovieCasting.setCasting(casting);
        existingMovieCasting.setRole(updatedMovieCasting.getRole());

        movieCastingRepository.save(existingMovieCasting);

        return existingMovieCasting;
    }

    public void deleteMovieCasting(Long id) {
        movieCastingRepository.deleteById(id);
    }

}
