package com.movieview360.movieview360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_casting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Casting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoUrl;

    @JsonBackReference
    @OneToMany(mappedBy = "casting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieCasting> movieCastings = new ArrayList<>();

}
