package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByGenderId(Long categoryId);
}
