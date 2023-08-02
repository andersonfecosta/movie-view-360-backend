package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieCategoryRepository;
import com.movieview360.movieview360.services.MovieCastingService;
import com.movieview360.movieview360.services.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MovieCategory> createMovieCategory(@RequestBody MovieCategory movieCategory) {
        MovieCategory createdMovieCategory = movieCategoryService.createMovieCategory(movieCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovieCategory);
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
