package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.converters.EntityResponseConverter;
import com.movieview360.movieview360.converters.MovieConverter;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
import com.movieview360.movieview360.response.MovieResponse;
import com.movieview360.movieview360.services.CastingService;
import com.movieview360.movieview360.services.MovieCastingService;
import com.movieview360.movieview360.services.MovieCategoryService;
import com.movieview360.movieview360.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private CastingService castingService;

    @Autowired
    private MovieCastingService movieCastingService;
    @Autowired
    private EntityResponseConverter entityResponseConverter;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(entityResponseConverter.convertToMovieResponse(movie));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);

        if (movie != null) {
            return ResponseEntity.ok(entityResponseConverter.convertToMovieResponse(movie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-gender")
    public ResponseEntity<List<MovieResponse>> getMoviesByCategoryId(@RequestParam("categoryId") Long categoryId) {
        List<Movie> movies = movieService.getMoviesByCategoryId(categoryId);

        if (!movies.isEmpty()) {
            List<MovieResponse> responses = new ArrayList<>();
            for (Movie movie: movies) {
                responses.add(entityResponseConverter.convertToMovieResponse(movie));
            }
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<MovieResponse>> createMovies(@RequestBody List<MovieRequest> movieRequests) {
        List<Movie> createdMovies = movieService.createMovies(movieRequests);

        List<MovieResponse> responses = new ArrayList<>();

        for (Movie movie: createdMovies) {
            responses.add(entityResponseConverter.convertToMovieResponse(movie));
        }
        if (!movieRequests.isEmpty()) {
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        Movie movie = movieService.updateMovie(id, movieRequest);

        if (movieRequest != null) {
            return ResponseEntity.ok(entityResponseConverter.convertToMovieResponse(movie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
