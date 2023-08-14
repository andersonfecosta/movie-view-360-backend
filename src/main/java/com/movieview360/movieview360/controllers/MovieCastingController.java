package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.converters.EntityResponseConverter;
import com.movieview360.movieview360.converters.MovieCastingConverter;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.response.MovieCastingResponse;
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

    @Autowired
    private MovieCastingService movieCastingService;
    @Autowired
    private CastingService castingService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieCastingConverter movieCastingConverter;
    @Autowired
    private EntityResponseConverter entityResponseConverter;

    @GetMapping
    public List<MovieCastingResponse> getAllMovieCastings() {
        List<MovieCasting> movieCastings = movieCastingService.getAllMovieCastings();
        List<MovieCastingResponse> responses = new ArrayList<>();

        for (MovieCasting movieCasting: movieCastings) {
            responses.add(entityResponseConverter.convertToMovieCastingResponse(movieCasting));
        }

        return responses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCastingResponse> getMovieCastingById(@PathVariable Long id) {
        MovieCasting movieCasting = movieCastingService.getMovieCastingById(id);
        if (movieCasting != null) {
            return ResponseEntity.ok(entityResponseConverter.convertToMovieCastingResponse(movieCasting));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<MovieCastingResponse>> createMovieCastings(@RequestBody List<MovieCastingRequest> castingsRequest,@PathVariable Long movieId) {
        List<MovieCastingResponse> createdCastings = new ArrayList<>();
        Movie movie = movieService.getMovieById(movieId);
        List<MovieCasting> movieCastings = movieCastingService.createMovieCastings(castingsRequest, movie);

        for (MovieCasting movieCasting: movieCastings) {
            createdCastings.add(entityResponseConverter.convertToMovieCastingResponse(movieCasting));
        }

        return ResponseEntity.ok(createdCastings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCastingResponse> updateMovieCasting(@PathVariable Long id, @RequestBody MovieCastingRequest updatedMovieCasting) {
        MovieCastingResponse updatedMovieCastingResult = new MovieCastingResponse();
        updatedMovieCastingResult = entityResponseConverter.convertToMovieCastingResponse(movieCastingService.updateMovieCasting(id, updatedMovieCasting));

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
