package com.rs.findyourpet.controller;

import static com.rs.findyourpet.converter.PetConverter.fromPetToResponse;
import static com.rs.findyourpet.converter.PetConverter.fromRequest;

import com.rs.findyourpet.dto.request.PetRequest;
import com.rs.findyourpet.dto.response.PetResponse;
import com.rs.findyourpet.service.OrganizationService;
import com.rs.findyourpet.service.PetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final OrganizationService organizationService;

    @PostMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@PathVariable long organizationId,
                                 @RequestBody @Valid PetRequest petRequest) {
        var organization = organizationService.findById(organizationId);

        var savedPet = petService.save(fromRequest(petRequest, organization));

        return fromPetToResponse(savedPet);
    }

    @GetMapping
    public List<PetResponse> findPets(@RequestParam(required = false) String city, @RequestParam(required = false) String petType,
                                      @RequestParam(required = false) String size, @RequestParam(required = false) String sex,
                                      @RequestParam(required = false) String color) {
        var pets = petService.findPets(city, petType, size, sex, color);

        return fromPetToResponse(pets);
    }
}
