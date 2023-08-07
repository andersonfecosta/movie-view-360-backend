package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.MovieCategory;
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

    @GetMapping
    public List<MovieCategory> getAllMovieCategories() {
        return movieCategoryService.getAllMovieCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCategory> getMovieCategoryById(@PathVariable Long id) {
        MovieCategory movieCategory = movieCategoryService.getMovieCategoryById(id);
        if (movieCategory != null) {
            return ResponseEntity.ok(movieCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<MovieCategory> createMovieCategories(@RequestBody List<MovieCategory> categories) {
        List<MovieCategory> createdCategories = new ArrayList<>();

        for (MovieCategory category : categories) {
            MovieCategory savedCategory = movieCategoryService.createMovieCategory(category);
            createdCategories.add(savedCategory);
        }

        return createdCategories;
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieCategory> updateMovieCategory(@PathVariable Long id, @RequestBody MovieCategory updatedMovieCategory) {
        MovieCategory updatedMovieCategoryResult = movieCategoryService.updateMovieCategory(id, updatedMovieCategory);
        if (updatedMovieCategoryResult != null) {
            return ResponseEntity.ok(updatedMovieCategoryResult);
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
