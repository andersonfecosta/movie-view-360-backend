package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
