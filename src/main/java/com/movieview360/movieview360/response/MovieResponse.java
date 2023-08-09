package com.movieview360.movieview360.response;

import com.movieview360.movieview360.request.MovieCastingRequest;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private Integer releaseDate;
    private Long genderId;
    private String imgUrl;
    private boolean isFavorite;
    private List<MovieCastingRequest> castings;
}
