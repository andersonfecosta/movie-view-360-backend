package com.movieview360.movieview360.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private List<MovieResponse> favoriteMoviesResponse = new ArrayList<>();
}
