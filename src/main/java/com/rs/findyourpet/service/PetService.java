package com.rs.findyourpet.service;

import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.repository.PetRepository;
import com.rs.findyourpet.repository.PetSpecification;
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

    public List<Pet> findPets(String city, String petType, String size, String sex, String color) {
        var petSpecification = PetSpecification.builder() // should i add is need a temporary home?
                .city(city)
                .color(color)
                .petType(petType)
                .size(size)
                .sex(sex)
                .build();

        return petRepository.findAll(petSpecification);
    }
}
