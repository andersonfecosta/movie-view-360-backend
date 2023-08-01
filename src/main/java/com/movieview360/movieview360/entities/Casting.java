package com.movieview360.movieview360.entities;

import jakarta.persistence.*;
import lombok.*;

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

}
