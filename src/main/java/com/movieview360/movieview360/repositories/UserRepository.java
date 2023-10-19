package com.movieview360.movieview360.repositories;

import com.movieview360.movieview360.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
