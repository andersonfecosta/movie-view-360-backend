package com.movieview360.movieview360.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieview360.movieview360.entities.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieCategoryResponse {

    private Long id;
    private String description;
    @JsonIgnore
    private List<MovieResponse> movies;
}
