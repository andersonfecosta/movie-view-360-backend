package com.movieview360.movieview360.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    private String title;
    private String description;
    private Integer releaseDate;
    private Long genderId;
    private String imgUrl;
    //private boolean isFavorite;
    private List<MovieCastingRequest> castings;
}
