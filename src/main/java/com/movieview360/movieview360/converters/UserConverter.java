package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import com.movieview360.movieview360.response.MovieResponse;
import com.movieview360.movieview360.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public User ConvertToUser(UserRequest userRequest) {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }
}
