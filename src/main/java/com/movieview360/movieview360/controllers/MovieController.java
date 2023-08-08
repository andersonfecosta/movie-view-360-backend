package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.CastingRequest;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private CastingService castingService;

    @Autowired
    private MovieCastingService movieCastingService;

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

    @GetMapping("/by-gender")
    public ResponseEntity<List<Movie>> getMoviesByCategoryId(@RequestParam("categoryId") Long categoryId) {
        List<Movie> movies = movieService.getMoviesByCategoryId(categoryId);

        if (!movies.isEmpty()) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Movie>> createMovies(@RequestBody List<MovieRequest> movieRequests) {
        List<Movie> createdMovies = movieService.createMovies(movieRequests);

        if (!movieRequests.isEmpty()) {
            return ResponseEntity.ok(createdMovies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        Movie movie = movieService.updateMovie(id, movieRequest);

        if (movieRequest != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*private void applyUpdatesToMovie(Movie, Map<String, Object> updates) {
        if (updates.containsKey("title")) {
            movie.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("description")) {
            movie.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("releaseDate")) {
            movie.setReleaseDate((Integer) updates.get("releaseDate"));
        }
        if (updates.containsKey("genderId")) {
            Long genderId = (Long) updates.get("genderId");
            MovieCategory gender = movieCategoryService.getMovieCategoryById(genderId);
            movie.setGender(gender);
        }
        if (updates.containsKey("imgUrl")) {
            movie.setImgUrl((String) updates.get("imgUrl"));
        }
        if (updates.containsKey("isFavorite")) {
            movie.setFavorite((Boolean) updates.get("isFavorite"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
