package com.findyourpet.service;

import com.findyourpet.domain.Pet;
import com.findyourpet.domain.PetQuery;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.PetRepository;
import com.findyourpet.repository.pet.PetQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final PetQueryRepository petQueryRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> findPets(PetQuery petQuery) {
        return petQueryRepository.findPets(petQuery);
    }

    public Pet findById(String petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("resource_not_found", "pet"));
    }

    public Pet editPet(String petId, PetRequest request) {
        var pet = findById(petId);

        var editedOrganization = buildEditedPet(pet, request, petId);

        return petRepository.save(editedOrganization);
    }

    public void deletePet(String petId) {
        petRepository.deleteById(petId);
    }

    private Pet buildEditedPet(Pet pet, PetRequest request, String petId) {
        pet.setId(petId);
        return pet.toBuilder()
                .color(request.getColor())
                .description(request.getDescription())
                .neutered(request.isNeutered())
                .type(request.getType())
                .sex(request.getSex())
                .age(request.getAge())
                .size(request.getSize())
                .build();
    }
}
