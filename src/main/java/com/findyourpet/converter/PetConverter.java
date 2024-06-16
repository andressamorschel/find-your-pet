package com.findyourpet.converter;

import static com.findyourpet.converter.ImageConverter.fromImagesToResponse;

import com.findyourpet.domain.Organization;
import com.findyourpet.domain.Pet;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.dto.response.PetResponse;
import java.util.List;
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
                .images(ImageConverter.fromImagesToResponse(pet.getImages()))
                .organizationDetails(OrganizationConverter.fromOrganizationToResponse(pet.getOrganization()))
                .build();
    }

    public static List<PetResponse> fromPetToResponse(List<Pet> pets) {
        return pets.stream()
                .map(PetConverter::fromPetToResponse)
                .toList();
    }
}
