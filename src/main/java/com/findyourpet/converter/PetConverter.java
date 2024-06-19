package com.findyourpet.converter;

import com.findyourpet.domain.Pet;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.dto.response.ImageResponse;
import com.findyourpet.dto.response.OrganizationResponse;
import com.findyourpet.dto.response.PetResponse;
import com.findyourpet.service.ImageService;
import com.findyourpet.service.OrganizationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetConverter {

    private final OrganizationService organizationService;

    private final ImageService imageService;

    private final OrganizationConverter organizationConverter;

    public static Pet fromRequest(PetRequest petRequest, String organizationId) {
        return Pet.builder()
                .organizationId(organizationId)
                .color(petRequest.getColor())
                .description(petRequest.getDescription())
                .neutered(petRequest.isNeutered())
                .type(petRequest.getType())
                .sex(petRequest.getSex())
                .size(petRequest.getSize())
                .age(petRequest.getAge())
                .build();
    }

    public PetResponse fromPetToResponse(Pet pet) {
        var images = getImages(pet.getId());
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
                .organizationDetails(getOrganizationDetails(pet.getOrganizationId()))
                .build();
    }

    public List<PetResponse> fromPetToResponse(List<Pet> pets) {
        return pets.stream()
                .map(this::fromPetToResponse)
                .toList();
    }

    private List<ImageResponse> getImages(String petId) {
        return imageService.getImaqeByPet(petId).stream()
                .map(ImageConverter::fromImagesToResponse)
                .toList();
    }

    private OrganizationResponse getOrganizationDetails(String organizationId) {
        var organization = organizationService.findById(organizationId);
        return organizationConverter.fromOrganizationToResponse(organization);
    }
}
