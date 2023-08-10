package com.movieview360.movieview360.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CastingResponse {

    private Long id;
    private String name;
    private String photoUrl;
    @JsonIgnore
    private List<MovieCastingResponse> movieCastingResponses;
}
