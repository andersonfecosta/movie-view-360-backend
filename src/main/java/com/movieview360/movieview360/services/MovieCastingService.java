package com.movieview360.movieview360.services;


import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.repositories.MovieCastingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MovieCasting createMovieCasting(MovieCasting movieCasting) {
        return movieCastingRepository.save(movieCasting);
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
