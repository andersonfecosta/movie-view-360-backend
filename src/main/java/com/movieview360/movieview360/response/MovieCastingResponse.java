package com.movieview360.movieview360.response;

import com.movieview360.movieview360.entities.Role;
import lombok.Data;

@Data
public class MovieCastingResponse {

    private Long id;
    private MovieResponse movie;
    private CastingResponse casting;
    private Role role;

}
