package com.movieview360.movieview360.services;

import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.repositories.CastingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastingService {

    @Autowired
    private CastingRepository castingRepository;

    public List<Casting> getAllCastings() {
        return castingRepository.findAll();
    }

    public Casting getCastingById(Long id) {
        return castingRepository.findById(id).orElse(null);
    }

    public Casting createCasting(Casting casting) {
        return castingRepository.save(casting);
    }

    public Casting updateCasting(Long id, Casting updatedCasting) {
        Casting casting = castingRepository.findById(id).orElse(null);
        if (casting != null) {
            casting.setName(updatedCasting.getName());
            return castingRepository.save(casting);
        }
        return null;
    }

    public void deleteCasting(Long id) {
        castingRepository.deleteById(id);
    }
}
