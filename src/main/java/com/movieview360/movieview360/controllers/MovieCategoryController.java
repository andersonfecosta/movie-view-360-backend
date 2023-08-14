package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.converters.EntityResponseConverter;
import com.movieview360.movieview360.converters.MovieCategoryConverter;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieCategoryRequest;
import com.movieview360.movieview360.response.MovieCategoryResponse;
import com.movieview360.movieview360.services.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class MovieCategoryController {

    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private MovieCategoryConverter movieCategoryConverter;
    @Autowired
    private EntityResponseConverter entityResponseConverter;

    @GetMapping
    public ResponseEntity<List<MovieCategoryResponse>> getAllMovieCategories() {
        List<MovieCategory> movieCategories = movieCategoryService.getAllMovieCategories();
        List<MovieCategoryResponse> responses = new ArrayList<>();

        for(MovieCategory movieCategory: movieCategories) {
            responses.add(entityResponseConverter.convertToMovieCategoryResponse(movieCategory));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCategoryResponse> getMovieCategoryById(@PathVariable Long id) {
        MovieCategory movieCategory = movieCategoryService.getMovieCategoryById(id);
        if (movieCategory != null) {
            return ResponseEntity.ok(entityResponseConverter.convertToMovieCategoryResponse(movieCategory));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<MovieCategoryResponse> createMovieCategories(@RequestBody List<MovieCategoryRequest> categories) {
        List<MovieCategory> createdCategories = movieCategoryService.createMovieCategories(categories);
        List<MovieCategoryResponse> responses = new ArrayList<>();

        for (MovieCategory movieCategory: createdCategories) {
            responses.add(entityResponseConverter.convertToMovieCategoryResponse(movieCategory));
        }

        return responses;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCategoryResponse> updateMovieCategory(@PathVariable Long id, @RequestBody MovieCategoryRequest updatedMovieCategory) {
        MovieCategory updatedMovieCategoryResult = movieCategoryService.updateMovieCategory(id, updatedMovieCategory);

        if (updatedMovieCategoryResult != null) {
            return ResponseEntity.ok(entityResponseConverter.convertToMovieCategoryResponse(updatedMovieCategoryResult));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieCategory(@PathVariable Long id) {
        movieCategoryService.deleteMovieCategory(id);
        return ResponseEntity.noContent().build();
    }
}
