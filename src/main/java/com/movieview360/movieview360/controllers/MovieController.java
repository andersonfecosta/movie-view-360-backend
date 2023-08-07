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

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<Movie>> getMoviesByCategoryId(@PathVariable Long categoryId) {
        List<Movie> movies = movieService.getMoviesByCategoryId(categoryId);

        if (!movies.isEmpty()) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Movie convertToMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setImgUrl(movieRequest.getImgUrl());
        //movie.setFavorite(movieRequest.isFavorite());

        MovieCategory movieCategory = new MovieCategory();
        movie.setGender(movieCategory);

        return movie;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> createMovies(@RequestBody List<MovieRequest> movieRequests) {
        List<Movie> createdMovies = new ArrayList<>();


        for (MovieRequest movieRequest : movieRequests) {
            Movie movie = convertToMovie(movieRequest);
            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            MovieCategory category = movieCategoryService.getMovieCategoryById(movieRequest.getGenderId());
            movie.setGender(category);
            movie.setImgUrl(movieRequest.getImgUrl());
            //movie.setFavorite(movieRequest.isFavorite());
            movie = movieService.createMovie(movie);

            List<MovieCasting> castings = new ArrayList<>();
            for (MovieCastingRequest castRequest : movieRequest.getCastings()) {
                Casting casting2 = castingService.getCastingById(castRequest.getCastingId());
                Casting casting = new Casting();
                casting.setName(casting2.getName());
                casting.setPhotoUrl(casting2.getPhotoUrl());
                casting = castingService.createCasting(casting);

                MovieCasting movieCasting = new MovieCasting();
                movieCasting.setMovie(movie);
                movieCasting.setCasting(casting);
                movieCasting.setRole(castRequest.getRole());

                movieCastingService.createMovieCasting(movieCasting);

                castings.add(movieCasting);
            }

            movie.setCastings(castings);

            createdMovies.add(movie);
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
