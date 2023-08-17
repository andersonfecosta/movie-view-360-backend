package com.movieview360.movieview360.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CastingRequest {

    private String name;
    private String photoUrl;

}
