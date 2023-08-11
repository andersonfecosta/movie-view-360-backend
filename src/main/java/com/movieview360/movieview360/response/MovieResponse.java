package com.movieview360.movieview360.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.request.MovieCastingRequest;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private Integer releaseDate;
    private MovieCategoryResponse gender;
    private String imgUrl;
    private boolean isFavorite = false;
    private List<MovieCastingResponse> castings;
}

