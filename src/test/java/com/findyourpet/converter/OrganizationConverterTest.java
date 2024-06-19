package com.findyourpet.converter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.findyourpet.domain.Address;
import com.findyourpet.domain.Organization;
import com.findyourpet.dto.request.AddressRequest;
import com.findyourpet.dto.request.OrganizationRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationConverterTest {

    private final OrganizationConverter organizationConverter = new OrganizationConverter();

    private Organization organization;

    private Address address;

    @BeforeEach
    public void setUp() {
        address = Address.builder()
                .city("Campo Bom")
                .neighborhood("Centro")
                .number("1998")
                .zipCode("93700000")
                .street("Av. dos Estados")
                .build();

        organization = Organization.builder()
                .address(address)
                .description("organization description")
                .instagramUrl("https://instagram.com/account")
                .responsibleDocument("08765432109")
                .whatsAppNumber("(51) 987654321")
                .build();
    }

    @Test
    void shouldFromRequestSuccessfully() {
        var addressRequest = AddressRequest.builder()
                .city("Campo Bom")
                .neighborhood("Centro")
                .number("1998")
                .zipCode("93700000")
                .street("Av. dos Estados")
                .build();

        var organizationRequest = OrganizationRequest.builder()
                .address(addressRequest)
                .description("organization description")
                .instagramUrl("https://instagram.com/account")
                .responsibleDocument("08765432109")
                .whatsAppNumber("(51) 987654321")
                .build();

        var response = organizationConverter.fromRequest(organizationRequest);

        assertThat(response.getDescription()).isEqualTo("organization description");
        assertThat(response.getInstagramUrl()).isEqualTo("https://instagram.com/account");
        assertThat(response.getResponsibleDocument()).isEqualTo("08765432109");
        assertThat(response.getWhatsAppNumber()).isEqualTo("(51) 987654321");

        var address = response.getAddress();

        assertThat(address.getCity()).isEqualTo("Campo Bom");
        assertThat(address.getNeighborhood()).isEqualTo("Centro");
        assertThat(address.getNumber()).isEqualTo("1998");
        assertThat(address.getZipCode()).isEqualTo("93700000");
        assertThat(address.getStreet()).isEqualTo("Av. dos Estados");
    }

    @Test
    void shouldFromOrganizationToResponseSuccessfully() {
        var response = organizationConverter.fromOrganizationToResponse(organization);

        assertThat(response.getDescription()).isEqualTo("organization description");
        assertThat(response.getInstagramUrl()).isEqualTo("https://instagram.com/account");
        assertThat(response.getResponsibleDocument()).isEqualTo("08765432109");
        assertThat(response.getWhatsAppNumber()).isEqualTo("(51) 987654321");

        var addressResponse = response.getAddress();

        assertThat(addressResponse.getCity()).isEqualTo("Campo Bom");
        assertThat(addressResponse.getNeighborhood()).isEqualTo("Centro");
        assertThat(addressResponse.getNumber()).isEqualTo("1998");
        assertThat(addressResponse.getZipCode()).isEqualTo("93700000");
        assertThat(addressResponse.getStreet()).isEqualTo("Av. dos Estados");
    }

    @Test
    void shouldFromOrganizationsToResponseSuccessfully() {
        var response = organizationConverter.fromOrganizationsToResponse(List.of(organization))
                .get(0);

        assertThat(response.getDescription()).isEqualTo("organization description");
        assertThat(response.getInstagramUrl()).isEqualTo("https://instagram.com/account");
        assertThat(response.getResponsibleDocument()).isEqualTo("08765432109");
        assertThat(response.getWhatsAppNumber()).isEqualTo("(51) 987654321");

        var addressResponse = response.getAddress();

        assertThat(addressResponse.getCity()).isEqualTo("Campo Bom");
        assertThat(addressResponse.getNeighborhood()).isEqualTo("Centro");
        assertThat(addressResponse.getNumber()).isEqualTo("1998");
        assertThat(addressResponse.getZipCode()).isEqualTo("93700000");
        assertThat(addressResponse.getStreet()).isEqualTo("Av. dos Estados");
    }
}
