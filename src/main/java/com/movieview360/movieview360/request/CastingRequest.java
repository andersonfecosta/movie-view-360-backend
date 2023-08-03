package com.movieview360.movieview360.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CastingRequest {

    private Long id;
    private String name;
    private String photoUrl;


}
