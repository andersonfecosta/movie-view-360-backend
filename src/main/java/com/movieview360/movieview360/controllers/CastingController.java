package com.movieview360.movieview360.controllers;


import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.repositories.CastingRepository;
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

    public ResponseEntity<Casting> getCastingById(@PathVariable Long id) {
        Casting casting = castingService.getCastingById(id);
        if (casting != null) {
            return ResponseEntity.ok(casting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Casting> createCastings(@RequestBody List<Casting> castings) {
        List<Casting> createdCastings = new ArrayList<>();

        for (Casting casting : castings) {
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
