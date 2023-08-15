package com.movieview360.movieview360.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ACTOR,
    DIRECTOR,
    PRODUCER,
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
