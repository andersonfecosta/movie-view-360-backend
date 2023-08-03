package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> createMovies(@RequestBody List<Movie> movies) {
        List<Movie> createdMovies = new ArrayList<>();

        for (Movie movie : movies) {
            Movie savedMovie = movieService.createMovie(movie);
            createdMovies.add(savedMovie);
        }

        return createdMovies;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie updatedMovieResult = movieService.updateMovie(id, updatedMovie);
        if (updatedMovieResult != null) {
            return ResponseEntity.ok(updatedMovieResult);
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
