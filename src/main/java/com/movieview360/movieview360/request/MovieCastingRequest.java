package com.movieview360.movieview360.request;

import com.movieview360.movieview360.entities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieCastingRequest {

    private Long movieId;
    private Long castingId;
    private Role role;


}
