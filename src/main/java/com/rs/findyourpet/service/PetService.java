package com.rs.findyourpet.service;

import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.repository.PetRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> findPets() {
        return petRepository.findAll();
    }
}
