package com.movieview360.movieview360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private Integer releaseDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private MovieCategory gender;

    @Column(nullable = false)
    private String imgUrl;

    @JsonIgnore
    @Column(nullable = false)
    private boolean isFavorite = false;

    @JsonBackReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieCasting> castings;

}
