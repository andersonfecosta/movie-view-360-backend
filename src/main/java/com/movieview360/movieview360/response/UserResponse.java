package com.movieview360.movieview360.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String password;
}
