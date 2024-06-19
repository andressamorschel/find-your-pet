package com.findyourpet.converter;

import static com.findyourpet.converter.PetConverter.fromRequest;
import static com.findyourpet.domain.enumerated.PetSex.FEMALE;
import static com.findyourpet.domain.enumerated.PetSex.MALE;
import static com.findyourpet.domain.enumerated.PetSize.MEDIUM;
import static com.findyourpet.domain.enumerated.PetType.CAT;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.findyourpet.domain.Image;
import com.findyourpet.domain.Organization;
import com.findyourpet.domain.Pet;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.dto.response.OrganizationResponse;
import com.findyourpet.service.ImageService;
import com.findyourpet.service.OrganizationService;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetConverterTest {

    @Mock
    private OrganizationService organizationService;

    @Mock
    private OrganizationConverter organizationConverter;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private PetConverter petConverter;

    private Pet pet;

    private String petId;

    private Organization organization;

    private String organizationId;

    @BeforeEach
    public void setUp() {
        organizationId = new ObjectId().toString();
        organization = mock(Organization.class);
        pet = Pet.builder()
                .color("black")
                .description("pet description")
                .needATemporaryHome(true)
                .neutered(true)
                .type(CAT)
                .sex(MALE)
                .size(MEDIUM)
                .organizationId(organizationId)
                .age(2)
                .build();
        pet.setId(petId);
    }

    @Test
    void shouldFromPetToResponseSuccessfully() {
        given(imageService.getImaqeByPet(petId)).willReturn(List.of(new Image()));
        given(organizationService.findById(organizationId)).willReturn(organization);
        given(organizationConverter.fromOrganizationToResponse(organization)).willReturn(new OrganizationResponse());
        var response = petConverter.fromPetToResponse(pet);

        assertThat(response.getAge()).isEqualTo(2);
        assertThat(response.getColor()).isEqualTo("black");
        assertThat(response.isNeutered()).isTrue();
        assertThat(response.getType()).isEqualTo(CAT);
        assertThat(response.getSex()).isEqualTo(MALE);
        assertThat(response.getSize()).isEqualTo(MEDIUM);
        assertThat(response.getOrganizationDetails()).isNotNull();
    }

    @Test
    void shouldFromRequestSuccessfully() {
        var organizationId = "organization-id";
        var petRequest = PetRequest.builder()
                .color("white")
                .description("white cat")
                .isNeutered(true)
                .age(1)
                .type(CAT)
                .sex(FEMALE)
                .size(MEDIUM)
                .build();

        var response = fromRequest(petRequest, organizationId);

        assertThat(response.getAge()).isEqualTo(1);
        assertThat(response.getOrganizationId()).isEqualTo(organizationId);
        assertThat(response.getColor()).isEqualTo("white");
        assertThat(response.isNeutered()).isTrue();
        assertThat(response.getType()).isEqualTo(CAT);
        assertThat(response.getSex()).isEqualTo(FEMALE);
        assertThat(response.getSize()).isEqualTo(MEDIUM);
    }
}