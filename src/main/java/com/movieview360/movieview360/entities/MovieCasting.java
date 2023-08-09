package com.movieview360.movieview360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "casting_id"}),name = "tb_movie_casting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieCasting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "casting_id", nullable = false)
    private Casting casting;

    @JsonProperty("castingName")
    public String getCastingName() {
        return casting.getName();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
