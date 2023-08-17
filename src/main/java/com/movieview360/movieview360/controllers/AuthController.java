package com.movieview360.movieview360.controllers;

import com.movieview360.movieview360.entities.User;
import com.movieview360.movieview360.request.UserRequest;
import com.movieview360.movieview360.response.LoginResponse;
import com.movieview360.movieview360.security.JwtService;
import com.movieview360.movieview360.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequest userRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
