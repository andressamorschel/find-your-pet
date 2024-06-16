package com.findyourpet.converter;

import static com.findyourpet.converter.OrganizationConverter.fromOrganizationToResponse;
import static java.util.Collections.emptyList;

import com.findyourpet.domain.Organization;
import com.findyourpet.domain.Pet;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.dto.response.PetResponse;
import java.util.List;
import java.util.Optional;
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
                .age(petRequest.getAge())
                .build();
    }

    public static PetResponse fromPetToResponse(Pet pet) {
        var images = Optional.ofNullable(pet.getImages())
                .map(ImageConverter::fromImagesToResponse)
                .orElse(emptyList());

        return PetResponse.builder()
                .color(pet.getColor())
                .description(pet.getDescription())
                .id(pet.getId())
                .isNeedATemporaryHome(pet.isNeedATemporaryHome())
                .isNeutered(pet.isNeutered())
                .type(pet.getType())
                .sex(pet.getSex())
                .size(pet.getSize())
                .age(pet.getAge())
                .images(images)
                .organizationDetails(fromOrganizationToResponse(pet.getOrganization()))
                .build();
    }

    public static List<PetResponse> fromPetToResponse(List<Pet> pets) {
        return pets.stream()
                .map(PetConverter::fromPetToResponse)
                .toList();
    }
}
