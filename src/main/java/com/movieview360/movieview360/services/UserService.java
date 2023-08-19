package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.Role;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    public boolean isAuthorized(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                userId.equals(((User) authentication.getPrincipal()).getId());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        if (isAuthorized(id)) {
            return userRepository.findById(id).orElse(null);
        }else {
            throw new AccessDeniedException("Access denied");
        }
    }

    public User createUser(User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        if (isAuthorized(id)) {
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                return userRepository.save(user);
            }else {
                throw new AccessDeniedException("Access denied");
            }
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addMovieToFavorites(Long userId, Long movieId) {
        if (isAuthorized(userId)) {
            User user = userRepository.findById(userId).orElse(null);
            Movie movie = movieRepository.findById(movieId).orElse(null);
            if (user != null && movie != null ) {
                user.getFavoriteMovies().add(movie);
                movie.setFavorite(true);
                userRepository.save(user);
            }else {
                throw new AccessDeniedException("Access denied");
            }
        }
    }

    public void removeMovieFromFavorites(Long userId, Long movieId) {
        if (isAuthorized(userId)) {
            User user = userRepository.findById(userId).orElse(null);
            Movie movie = movieRepository.findById(movieId).orElse(null);
            if (user != null && movie != null) {
                user.getFavoriteMovies().remove(movie);
                movie.setFavorite(false);
                userRepository.save(user);
            }else {
                throw new AccessDeniedException("Access denied");
            }
        }
    }

    public List<Movie> getFavoriteMovies(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            if (isAuthorized(userId)) {
                return user.getFavoriteMovies();
            } else {
                throw new AccessDeniedException("Access denied");
            }
        } else {
            return new ArrayList<>();
        }
    }
}
