package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User userConverter(UserRequest userRequest) {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }
}
