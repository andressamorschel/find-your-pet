package com.rs.findyourpet.converter;

import static com.rs.findyourpet.converter.AddressConverter.fromAddressRequest;
import static com.rs.findyourpet.converter.AddressConverter.fromAddressToResponse;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.rs.findyourpet.domain.Address;
import com.rs.findyourpet.dto.request.AddressRequest;
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

        var response = fromAddressRequest(addressRequest);

        assertThat(response.getCity()).isEqualTo("Campo Bom");
        assertThat(response.getNeighborhood()).isEqualTo("Centro");
        assertThat(response.getNumber()).isEqualTo("7609");
        assertThat(response.getZipCode()).isEqualTo("9370000");
        assertThat(response.getStreet()).isEqualTo("Av. Brasil");
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

        var response = fromAddressToResponse(address);

        assertThat(response.getCity()).isEqualTo("Campo Bom");
        assertThat(response.getNeighborhood()).isEqualTo("Centro");
        assertThat(response.getNumber()).isEqualTo("7609");
        assertThat(response.getZipCode()).isEqualTo("9370000");
        assertThat(response.getStreet()).isEqualTo("Av. Brasil");
    }

}
