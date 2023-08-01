package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCategoryService {

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    public List<MovieCategory> getAllMovieCategories() {
        return movieCategoryRepository.findAll();
    }

    public MovieCategory getMovieCategoryById(Long id) {
        return movieCategoryRepository.findById(id).orElse(null);
    }

    public MovieCategory createMovieCategory(MovieCategory movieCategory) {
        return movieCategoryRepository.save(movieCategory);
    }

    public MovieCategory updateMovieCategory(Long id, MovieCategory updatedMovieCategory) {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).orElse(null);
        if (movieCategory != null) {
            movieCategory.setName(updatedMovieCategory.getName());
            return movieCategoryRepository.save(movieCategory);
        }
        return null;
    }

    public void deleteMovieCategory(Long id) {
        movieCategoryRepository.deleteById(id);
    }

}
