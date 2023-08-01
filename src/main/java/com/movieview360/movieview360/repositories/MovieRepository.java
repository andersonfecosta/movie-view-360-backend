package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
