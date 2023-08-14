package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.request.CastingRequest;
import org.springframework.stereotype.Component;

@Component
public class CastingConverter {

    public Casting castingConverter(CastingRequest castingRequest) {
        return Casting.builder()
                .name(castingRequest.getName())
                .photoUrl(castingRequest.getPhotoUrl())
                .build();
    }



}
