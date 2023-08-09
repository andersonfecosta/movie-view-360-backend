package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieCategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class MovieCategoryConverter {

    public MovieCategory movieCategoryConverter(MovieCategoryRequest movieCategoryRequest) {
        MovieCategory movieCategory = new MovieCategory();

        movieCategory.setDescription(movieCategoryRequest.getDescription());


        return movieCategory;
    }
}
