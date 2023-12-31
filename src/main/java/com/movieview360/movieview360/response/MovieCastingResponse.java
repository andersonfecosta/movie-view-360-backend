package com.movieview360.movieview360.response;

import com.movieview360.movieview360.entities.Role;
import lombok.Data;

@Data
public class MovieCastingResponse {

    private Long id;
    private String name;
    private String photoUrl;
    private Role role;

}
