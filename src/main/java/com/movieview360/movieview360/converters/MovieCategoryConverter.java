package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieCategoryRequest;
import com.movieview360.movieview360.response.MovieCategoryResponse;
import com.movieview360.movieview360.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieCategoryConverter {

    public MovieCategory movieCategoryConverter(MovieCategoryRequest movieCategoryRequest) {
        MovieCategory movieCategory = new MovieCategory();

        movieCategory.setDescription(movieCategoryRequest.getDescription());

        return movieCategory;
    }
}
