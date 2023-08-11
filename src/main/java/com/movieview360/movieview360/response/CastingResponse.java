package com.movieview360.movieview360.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class CastingResponse {

    private Long id;
    private String name;
    private String photoUrl;
    @JsonIgnore
    @JsonManagedReference
    private List<MovieCastingResponse> movieCastingResponses;
}
