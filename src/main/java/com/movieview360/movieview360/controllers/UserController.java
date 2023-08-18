package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import com.movieview360.movieview360.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    public User convertToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User createdUser = convertToUser(userRequest);
        User user = userService.createUser(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User updatedUserResult = userService.updateUser(id, updatedUser);
        if (updatedUserResult != null) {
            return ResponseEntity.ok(updatedUserResult);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/favorite-movies")
    public ResponseEntity<List<Movie>> getFavoriteMovies(@PathVariable Long userId) {
        List<Movie> favoriteMovies = userService.getFavoriteMovies(userId);
        return ResponseEntity.ok(favoriteMovies);
    }
    @PostMapping("/{userId}/favorite-movies/{movieId}")
    public ResponseEntity<String> addMovieToFavorites(@PathVariable Long userId, @PathVariable Long movieId) {
        userService.addMovieToFavorites(userId, movieId);
        return ResponseEntity.ok("Movie added to favorites");
    }

    @DeleteMapping("/{userId}/favorite-movies/{movieId}")
    public ResponseEntity<String> removeMovieFromFavorites(
            @PathVariable Long userId,
            @PathVariable Long movieId) {
        userService.removeMovieFromFavorites(userId, movieId);
        return ResponseEntity.ok("Movie removed from favorites");
    }
}
