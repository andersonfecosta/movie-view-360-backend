package com.movieview360.movieview360.services;

import com.movieview360.movieview360.converters.CastingConverter;
import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.repositories.CastingRepository;
import com.movieview360.movieview360.request.CastingRequest;
import com.movieview360.movieview360.response.CastingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CastingService {

    @Autowired
    private CastingRepository castingRepository;
    @Autowired
    private CastingConverter castingConverter;

    public List<Casting> getAllCastings() {
        return castingRepository.findAll();
    }

    public Casting getCastingById(Long id) {
        return castingRepository.findById(id).orElse(null);
    }

    public List<Casting> createCasting(List<CastingRequest> castingRequest) {
        List<Casting> castings = new ArrayList<>();

        for (CastingRequest casting: castingRequest) {
            castings.add(castingConverter.castingConverter(casting));
        }
        castingRepository.saveAll(castings);
        return castings;
    }

    public Casting updateCasting(Long id, CastingRequest updatedCasting) {
        Casting casting = castingConverter.castingConverter(updatedCasting);
        if (casting != null) {
            castingRepository.save(casting);
            return casting;
        }
        return null;
    }

    public void deleteCasting(Long id) {
        castingRepository.deleteById(id);
    }

    public List<Casting> autocompleteCasting(String query) {
        return castingRepository.findByNameContainingIgnoreCase(query);
    }

    public Casting saveCasting(Casting casting) {
        return castingRepository.save(casting);
    }
}
