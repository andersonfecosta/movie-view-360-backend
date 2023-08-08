package com.movieview360.movieview360.services;


import com.movieview360.movieview360.converters.MovieCastingConverter;
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

    public List<MovieCasting> getAllMovieCastings() {
        return movieCastingRepository.findAll();
    }

    public MovieCasting getMovieCastingById(Long id) {
        return movieCastingRepository.findById(id).orElse(null);
    }

    public List<MovieCasting> createMovieCastings(List<MovieCastingRequest> castingRequests) {
        List<MovieCasting> movieCastings = new ArrayList<>();
        MovieCastingConverter converter = new MovieCastingConverter();

        for (MovieCastingRequest castingRequest : castingRequests) {
            MovieCasting movieCasting = converter.convertToMovieCasting(castingRequest);
            movieCastings.add(movieCasting);
        }

        return movieCastingRepository.saveAll(movieCastings);
    }

    public MovieCasting updateMovieCasting(Long id, MovieCasting updatedMovieCasting) {
        MovieCasting movieCasting = movieCastingRepository.findById(id).orElse(null);
        if (movieCasting != null) {
            movieCasting.setMovie(updatedMovieCasting.getMovie());
            movieCasting.setCasting(updatedMovieCasting.getCasting());
            movieCasting.setRole(updatedMovieCasting.getRole());
            return movieCastingRepository.save(movieCasting);
        }
        return null;
    }

    public void deleteMovieCasting(Long id) {
        movieCastingRepository.deleteById(id);
    }

}
