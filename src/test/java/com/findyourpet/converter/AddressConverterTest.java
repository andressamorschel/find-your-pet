package com.findyourpet.converter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.findyourpet.domain.Address;
import com.findyourpet.dto.request.AddressRequest;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressConverterTest {

    @Test
    void shouldFromAddressRequestSuccessfully() {
        var addressRequest = AddressRequest.builder()
                .number("7609")
                .street("Av. Brasil")
                .neighborhood("Centro")
                .zipCode("9370000")
                .city("Campo Bom")
                .build();

        var response = AddressConverter.fromAddressRequest(addressRequest);

        AssertionsForClassTypes.assertThat(response.getCity()).isEqualTo("Campo Bom");
        AssertionsForClassTypes.assertThat(response.getNeighborhood()).isEqualTo("Centro");
        AssertionsForClassTypes.assertThat(response.getNumber()).isEqualTo("7609");
        AssertionsForClassTypes.assertThat(response.getZipCode()).isEqualTo("9370000");
        AssertionsForClassTypes.assertThat(response.getStreet()).isEqualTo("Av. Brasil");
    }

    @Test
    void shouldFromAddressToResponseSuccessfully() {
        var address = Address.builder()
                .id(9L)
                .number("7609")
                .street("Av. Brasil")
                .neighborhood("Centro")
                .zipCode("9370000")
                .city("Campo Bom")
                .build();

        var response = AddressConverter.fromAddressToResponse(address);

        AssertionsForClassTypes.assertThat(response.getCity()).isEqualTo("Campo Bom");
        AssertionsForClassTypes.assertThat(response.getNeighborhood()).isEqualTo("Centro");
        AssertionsForClassTypes.assertThat(response.getNumber()).isEqualTo("7609");
        AssertionsForClassTypes.assertThat(response.getZipCode()).isEqualTo("9370000");
        AssertionsForClassTypes.assertThat(response.getStreet()).isEqualTo("Av. Brasil");
    }

}
