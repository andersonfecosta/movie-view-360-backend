package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.request.CastingRequest;
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

    @GetMapping
    public List<Casting> getAllCastings() {
        return castingService.getAllCastings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Casting> getCastingById(@PathVariable Long id) {
        Casting casting = castingService.getCastingById(id);
        if (casting != null) {
            return ResponseEntity.ok(casting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<Casting>> autocompleteCasting(@RequestParam("query") String query) {

        List<Casting> castings = castingService.autocompleteCasting(query);
        return ResponseEntity.ok(castings);
    }

    public Casting convertCasting(CastingRequest castingRequest) {
        Casting casting = new Casting();
        casting.setName(castingRequest.getName());
        casting.setPhotoUrl(castingRequest.getPhotoUrl());
        return casting;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Casting> createCastings(@RequestBody List<CastingRequest> castingRequests) {
        List<Casting> createdCastings = new ArrayList<>();

        for (CastingRequest castingRequest : castingRequests) {
            Casting casting = convertCasting(castingRequest);
            Casting savedCasting = castingService.createCasting(casting);
            createdCastings.add(savedCasting);
        }

        return createdCastings;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Casting> updateCasting(@PathVariable Long id, @RequestBody Casting updatedCasting) {
        Casting updatedCastingResult = castingService.updateCasting(id, updatedCasting);
        if (updatedCastingResult != null) {
            return ResponseEntity.ok(updatedCastingResult);
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
