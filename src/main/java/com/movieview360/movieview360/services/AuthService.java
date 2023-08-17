package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Role;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    public void init() {
        User adminUser = User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .role(Role.ADMIN)
                .email("admin@example.com")
                .build();

        userRepository.save(adminUser);
    }
}
