package com.findyourpet.converter;

import static com.findyourpet.converter.PetConverter.fromPetToResponse;
import static com.findyourpet.converter.PetConverter.fromRequest;
import static com.findyourpet.domain.enumerated.PetSex.FEMALE;
import static com.findyourpet.domain.enumerated.PetSex.MALE;
import static com.findyourpet.domain.enumerated.PetSize.MEDIUM;
import static com.findyourpet.domain.enumerated.PetType.CAT;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

import com.findyourpet.domain.Address;
import com.findyourpet.domain.Organization;
import com.findyourpet.domain.Pet;
import com.findyourpet.dto.request.PetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetConverterTest {

    private Pet pet;

    @BeforeEach
    public void setUp() {
        var address = Address.builder()
                .id(123L)
                .city("Campo Bom")
                .neighborhood("Centro")
                .number("1998")
                .zipCode("93700000")
                .street("Av. dos Estados")
                .build();

        var organization = Organization.builder()
                .id(9L)
                .address(address)
                .description("organization description")
                .instagramUrl("https://instagram.com/account")
                .responsibleDocument("08765432109")
                .whatsAppNumber("(51) 987654321")
                .build();

        pet = Pet.builder()
                .color("black")
                .description("pet description")
                .id(1L)
                .needATemporaryHome(true)
                .neutered(true)
                .type(CAT)
                .sex(MALE)
                .size(MEDIUM)
                .organization(organization)
                .age(2)
                .build();
    }

    @Test
    void shouldFromPetToResponseSuccessfully() {
        var response = fromPetToResponse(pet);

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
        var organization = mock(Organization.class);
        var petRequest = PetRequest.builder()
                .color("white")
                .description("white cat")
                .isNeutered(true)
                .age(1)
                .type(CAT)
                .sex(FEMALE)
                .size(MEDIUM)
                .build();

        var response = fromRequest(petRequest, organization);

        assertThat(response.getAge()).isEqualTo(1);
        assertThat(response.getOrganization()).isEqualTo(organization);
        assertThat(response.getColor()).isEqualTo("white");
        assertThat(response.isNeutered()).isTrue();
        assertThat(response.getType()).isEqualTo(CAT);
        assertThat(response.getSex()).isEqualTo(FEMALE);
        assertThat(response.getSize()).isEqualTo(MEDIUM);
    }
}