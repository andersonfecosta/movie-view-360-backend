package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setGender(updatedMovie.getGender());
            movie.setCastings(updatedMovie.getCastings());
            return movieRepository.save(movie);
        }
        return null;
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMoviesByCategoryId(Long categoryId) {
        return movieRepository.findByGenderId(categoryId);
    }
}
