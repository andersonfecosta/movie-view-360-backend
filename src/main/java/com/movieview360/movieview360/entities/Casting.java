package com.movieview360.movieview360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_casting")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Casting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoUrl;

    @JsonBackReference
    @OneToMany(mappedBy = "casting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieCasting> movieCastings;

    public void addMovieCasting(MovieCasting movieCasting) {
        movieCastings.add(movieCasting);
        movieCasting.setCasting(this);
    }

}
