package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
}
