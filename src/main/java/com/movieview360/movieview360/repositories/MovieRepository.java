package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    Optional<Movie> findById(Long id);
    List<Movie> findByGenderId(Long categoryId);
}
