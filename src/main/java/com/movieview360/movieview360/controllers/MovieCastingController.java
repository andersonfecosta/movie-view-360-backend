package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.services.CastingService;
import com.movieview360.movieview360.services.MovieCastingService;
import com.movieview360.movieview360.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movie-castings")
public class MovieCastingController {

    private final MovieCastingService movieCastingService;
    private final CastingService castingService;
    private final MovieService movieService;

    public MovieCastingController(MovieCastingService movieCastingService, CastingService castingService, MovieService movieService) {
        this.movieCastingService = movieCastingService;
        this.castingService = castingService;
        this.movieService = movieService;
    }



    @GetMapping
    public List<MovieCasting> getAllMovieCastings() {
        return movieCastingService.getAllMovieCastings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCasting> getMovieCastingById(@PathVariable Long id) {
        MovieCasting movieCasting = movieCastingService.getMovieCastingById(id);
        if (movieCasting != null) {
            return ResponseEntity.ok(movieCasting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<MovieCasting> createMovieCastings(@RequestBody List<MovieCasting> castings) {
        List<MovieCasting> createdCastings = new ArrayList<>();

        for (MovieCasting casting : castings) {
            MovieCasting savedCasting = movieCastingService.createMovieCasting(casting);
            createdCastings.add(savedCasting);
        }

        return createdCastings;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCasting> updateMovieCasting(@PathVariable Long id, @RequestBody MovieCasting updatedMovieCasting) {
        MovieCasting updatedMovieCastingResult = movieCastingService.updateMovieCasting(id, updatedMovieCasting);
        if (updatedMovieCastingResult != null) {
            return ResponseEntity.ok(updatedMovieCastingResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieCasting(@PathVariable Long id) {
        movieCastingService.deleteMovieCasting(id);
        return ResponseEntity.noContent().build();
    }
}
