package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.services.MovieCastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie-castings")
public class MovieCastingController {

    @Autowired
    private MovieCastingService movieCastingService;

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
    public ResponseEntity<MovieCasting> createMovieCasting(@RequestBody MovieCasting movieCasting) {
        MovieCasting createdMovieCasting = movieCastingService.createMovieCasting(movieCasting);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovieCasting);
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
