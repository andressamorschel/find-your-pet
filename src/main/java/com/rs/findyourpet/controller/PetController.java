package com.rs.findyourpet.controller;

import static com.rs.findyourpet.converter.PetConverter.fromRequest;

import com.rs.findyourpet.converter.PetConverter;
import com.rs.findyourpet.dto.request.PetRequest;
import com.rs.findyourpet.dto.response.PetResponse;
import com.rs.findyourpet.service.OrganizationService;
import com.rs.findyourpet.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public PetResponse createPet(@PathVariable long organizationId, @RequestBody @Valid PetRequest petRequest) {
        var organization = organizationService.findById(organizationId);

        var savedPet = petService.save(fromRequest(petRequest, organization));

        return PetConverter.fromPetToResponse(savedPet);
    }
}
