package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.Casting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastingRepository extends JpaRepository<Casting,Long> {

    List<Casting> findByNameContainingIgnoreCase(String query);
}
