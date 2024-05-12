package com.rs.findyourpet.converter;

import static com.rs.findyourpet.converter.OrganizationConverter.fromOrganizationToResponse;

import com.rs.findyourpet.domain.Organization;
import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.dto.request.PetRequest;
import com.rs.findyourpet.dto.response.PetResponse;
import org.springframework.stereotype.Component;

@Component
public class PetConverter {

    public static Pet fromRequest(PetRequest petRequest, Organization organization) {
        return Pet.builder()
                .organization(organization)
                .color(petRequest.getColor())
                .description(petRequest.getDescription())
                .neutered(petRequest.isNeutered())
                .type(petRequest.getType())
                .sex(petRequest.getSex())
                .size(petRequest.getSize())
                .build();
    }

    public static PetResponse fromPetToResponse(Pet pet) {
        return PetResponse.builder()
                .age(pet.getAge())
                .color(pet.getColor())
                .description(pet.getDescription())
                .id(pet.getId())
                .isNeedATemporaryHome(pet.isNeedATemporaryHome())
                .isNeutered(pet.isNeutered())
                .type(pet.getType())
                .sex(pet.getSex())
                .size(pet.getSize())
                .organizationDetails(fromOrganizationToResponse(pet.getOrganization()))
                .build();
    }
}
