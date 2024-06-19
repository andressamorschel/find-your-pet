package com.findyourpet.controller;

import static com.findyourpet.converter.PetConverter.fromRequest;

import com.findyourpet.converter.PetConverter;
import com.findyourpet.converter.PetQueryConverter;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.dto.response.PetResponse;
import com.findyourpet.service.PetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets")
public class PetController {

    private final PetService petService;

    private final PetConverter petConverter;

    private final PetQueryConverter petQueryConverter;

    @PostMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@PathVariable String organizationId,
                                 @RequestBody @Valid PetRequest petRequest) {
        var savedPet = petService.save(fromRequest(petRequest, organizationId));

        return petConverter.fromPetToResponse(savedPet);
    }

    @GetMapping
    public List<PetResponse> findPets(@RequestParam(required = false) String city, @RequestParam(required = false) String petType,
                                      @RequestParam(required = false) String size, @RequestParam(required = false) String sex,
                                      @RequestParam(required = false) String color, @RequestParam(required = false) String organizationId) {
        var petQuery = petQueryConverter.buildPetQuery(city, petType, size, sex, color, organizationId);
        var pets = petService.findPets(petQuery);

        return petConverter.fromPetToResponse(pets);
    }

    @PutMapping("/{petId}")
    public PetResponse editPet(@PathVariable String petId, @RequestBody @Valid PetRequest petRequest) {
        var updated = petService.editPet(petId, petRequest);

        return petConverter.fromPetToResponse(updated);
    }

    @DeleteMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable String petId) {
        petService.deletePet(petId);
    }
}
