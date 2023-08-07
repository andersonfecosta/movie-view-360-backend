package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addMovieToFavorites(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (user != null && movie != null) {
            user.getFavoriteMovies().add(movie);
            movie.setFavorite(true);
            userRepository.save(user);
        }
    }

    public void removeMovieFromFavorites(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (user != null && movie != null) {
            user.getFavoriteMovies().remove(movie);
            movie.setFavorite(false);
            userRepository.save(user);
        }
    }

    public List<Movie> getFavoriteMovies(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getFavoriteMovies();
        } else {
            return new ArrayList<>();
        }
    }
}
