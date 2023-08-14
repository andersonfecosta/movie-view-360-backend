package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.converters.CastingConverter;
import com.movieview360.movieview360.converters.EntityResponseConverter;
import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.request.CastingRequest;
import com.movieview360.movieview360.response.CastingResponse;
import com.movieview360.movieview360.services.CastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/castings")
public class CastingController {

    @Autowired
    private CastingService castingService;
    @Autowired
    private CastingConverter castingConverter;
    @Autowired
    private EntityResponseConverter entityResponseConverter;

    @GetMapping
    public List<CastingResponse> getAllCastings() {
        List<CastingResponse> responses = new ArrayList<>();
        List<Casting> castings = castingService.getAllCastings();

        for (Casting casting: castings) {
            responses.add(entityResponseConverter.convertoToCastingResponse(casting));
        }

        return responses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CastingResponse> getCastingById(@PathVariable Long id) {
        Casting casting = castingService.getCastingById(id);
        CastingResponse response = entityResponseConverter.convertoToCastingResponse(casting);
        if (casting != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<CastingResponse>> autocompleteCasting(@RequestParam("query") String query) {

        List<Casting> castings = castingService.autocompleteCasting(query);
        List<CastingResponse> responses = new ArrayList<>();

        for (Casting casting: castings) {
            responses.add(entityResponseConverter.convertoToCastingResponse(casting));
        }

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<CastingResponse> createCastings(@RequestBody List<CastingRequest> castingRequests) {
        List<Casting> createdCastings = castingService.createCasting(castingRequests);
        List<CastingResponse> responses = new ArrayList<>();

        for (Casting casting: createdCastings) {
            responses.add(entityResponseConverter.convertoToCastingResponse(casting));
        }

        return responses;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CastingResponse> updateCasting(@PathVariable Long id, @RequestBody CastingRequest updatedCasting) {
        Casting updatedCastingResult = castingService.updateCasting(id, updatedCasting);
        CastingResponse response = entityResponseConverter.convertoToCastingResponse(updatedCastingResult);
        if (updatedCastingResult != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCasting(@PathVariable Long id) {
        castingService.deleteCasting(id);
        return ResponseEntity.noContent().build();
    }

}
