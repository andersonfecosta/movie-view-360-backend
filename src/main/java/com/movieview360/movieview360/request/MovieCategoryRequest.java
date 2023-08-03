package com.movieview360.movieview360.request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MovieCategoryRequest {

    private Long id;
    private String description;

}
