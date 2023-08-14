package com.movieview360.movieview360.services;

import com.movieview360.movieview360.converters.MovieCategoryConverter;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieCategoryRepository;
import com.movieview360.movieview360.request.MovieCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCategoryService {

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;
    @Autowired
    private MovieCategoryConverter movieCategoryConverter;

    public List<MovieCategory> getAllMovieCategories() {
        return movieCategoryRepository.findAll();
    }

    public MovieCategory getMovieCategoryById(Long id) {
        return movieCategoryRepository.findById(id).orElse(null);
    }

    public List<MovieCategory> createMovieCategories(List<MovieCategoryRequest> movieCategoryRequest) {
        List<MovieCategory> movieCategories = new ArrayList<>();

        for (MovieCategoryRequest request: movieCategoryRequest) {
            movieCategories.add(movieCategoryConverter.movieCategoryConverter(request));
        }
        movieCategoryRepository.saveAll(movieCategories);
        return movieCategories;
    }

    public MovieCategory updateMovieCategory(Long id, MovieCategoryRequest updatedMovieCategory) {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).orElse(null);
        if (movieCategory != null) {
            movieCategory = movieCategoryConverter.movieCategoryConverter(updatedMovieCategory);
            return movieCategoryRepository.save(movieCategory);
        }
        return null;
    }

    public void deleteMovieCategory(Long id) {
        movieCategoryRepository.deleteById(id);
    }

}
