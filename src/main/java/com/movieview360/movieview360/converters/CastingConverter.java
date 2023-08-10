package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.request.CastingRequest;
import com.movieview360.movieview360.response.CastingResponse;
import com.movieview360.movieview360.response.MovieCastingResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastingConverter {

    public Casting castingConverter(CastingRequest castingRequest) {
        Casting casting = new Casting();

        casting.setName(castingRequest.getName());
        casting.setPhotoUrl(castingRequest.getPhotoUrl());

        return casting;
    }



}
