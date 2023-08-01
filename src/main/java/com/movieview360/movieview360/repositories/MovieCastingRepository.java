package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.MovieCasting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastingRepository extends JpaRepository<MovieCasting, Long> {
}
