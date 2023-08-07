package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

    public MovieRequest getMovieRequestById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));

        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setTitle(movie.getTitle());
        movieRequest.setDescription(movie.getDescription());
        movieRequest.setReleaseDate(movie.getReleaseDate());
        movieRequest.setGenderId(movie.getGender().getId());
        movieRequest.setImgUrl(movie.getImgUrl());
        movieRequest.setFavorite(movie.isFavorite());

        List<MovieCastingRequest> castingRequests = new ArrayList<>();
        for (MovieCasting casting : movie.getCastings()) {
            MovieCastingRequest castingRequest = new MovieCastingRequest();
            castingRequest.setCastingId(casting.getCasting().getId());
            castingRequest.setRole(casting.getRole());
            castingRequests.add(castingRequest);
        }

        movieRequest.setCastings(castingRequests);

        return movieRequest;
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
