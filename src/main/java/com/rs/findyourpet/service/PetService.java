package com.rs.findyourpet.service;

import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.dto.request.PetRequest;
import com.rs.findyourpet.exceptions.NotFoundException;
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
        var petSpecification = PetSpecification.builder()
                .city(city)
                .color(color)
                .petType(petType)
                .size(size)
                .sex(sex)
                .build();

        return petRepository.findAll(petSpecification);
    }

    public Pet findById(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("400.013", Long.toString(petId)));
    }

    public Pet editPet(long petId, PetRequest request) {
        var pet = findById(petId);

        var editedOrganization = buildEditedPet(pet, request, petId);

        return petRepository.save(editedOrganization);
    }

    public void deletePet(long petId) {
        petRepository.deleteById(petId);
    }

    private Pet buildEditedPet(Pet pet, PetRequest request, long petId) {
        return pet.toBuilder()
                .id(petId)
                .color(request.getColor())
                .description(request.getDescription())
                .neutered(request.isNeutered())
                .type(request.getType())
                .sex(request.getSex())
                .size(request.getSize())
                .build();
    }
}
