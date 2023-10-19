package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.converters.EntityResponseConverter;
import com.movieview360.movieview360.converters.UserConverter;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import com.movieview360.movieview360.response.MovieSummaryResponse;
import com.movieview360.movieview360.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private EntityResponseConverter entityResponseConverter;

    @GetMapping("/all")
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
    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        boolean isUsernameUnique = userService.isUsernameUnique(username);
        return ResponseEntity.ok(isUsernameUnique);
    }
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isEmailUnique = userService.isEmailUnique(email);
        return ResponseEntity.ok(isEmailUnique);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User createdUser = userConverter.convertToUser(userRequest);
        if (!userService.isUsernameUnique(createdUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        if (!userService.isEmailUnique(createdUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        User user = userService.createUser(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUser) {
        User upUser = userConverter.convertToUser(updatedUser);
        User user = userService.updateUser(id, upUser);
        if (user != null) {
            return ResponseEntity.ok(user);
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
    public ResponseEntity<List<MovieSummaryResponse>> getFavoriteMovies(@PathVariable Long userId) {
        List<Movie> favoriteMovies = userService.getFavoriteMovies(userId);
        List<MovieSummaryResponse> responses = new ArrayList<>();

        for (Movie movie: favoriteMovies) {
            responses.add(entityResponseConverter.convertToMovieSummaryResponse(movie));
        }
        if (!favoriteMovies.isEmpty()) {
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping("/{userId}/favorite-movies/{movieId}")
    public ResponseEntity<String> addMovieToFavorites(@PathVariable Long userId, @PathVariable Long movieId) {
        userService.addMovieToFavorites(userId, movieId);
        return ResponseEntity.ok("Movie added to favorites");
    }

    @DeleteMapping("/{userId}/favorite-movies/{movieId}/remove")
    public ResponseEntity<String> removeMovieFromFavorites(
            @PathVariable Long userId,
            @PathVariable Long movieId) {
        userService.removeMovieFromFavorites(userId, movieId);
        return ResponseEntity.ok("Movie removed from favorites");
    }
}
