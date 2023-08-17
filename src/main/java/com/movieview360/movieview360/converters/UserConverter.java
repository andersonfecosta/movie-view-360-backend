package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.Role;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import com.movieview360.movieview360.response.MovieResponse;
import com.movieview360.movieview360.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {


    public User convertToUser(UserRequest userRequest) {
        User user = new User();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encryptedPassword);

        return user;
    }
}
