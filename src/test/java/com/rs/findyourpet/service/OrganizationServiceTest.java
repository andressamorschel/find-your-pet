package com.rs.findyourpet.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.rs.findyourpet.domain.Organization;
import com.rs.findyourpet.dto.request.AddressRequest;
import com.rs.findyourpet.dto.request.OrganizationRequest;
import com.rs.findyourpet.exceptions.NotFoundException;
import com.rs.findyourpet.repository.OrganizationRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @Captor
    private ArgumentCaptor<Organization> organizationCaptor;

    private long organizationId;

    @BeforeEach
    public void setUp() {
        organizationId = 1L;
    }

    @Test
    void shouldFindSuccessfully() {
        given(organizationRepository.findAll()).willReturn(List.of(new Organization()));

        var response = organizationService.find();

        assertThat(response).isNotEmpty();
        verify(organizationRepository).findAll();
    }

    @Test
    void shouldFindByIdSuccessfully() {
        var organization = mock(Organization.class);
        given(organizationRepository.findById(organizationId)).willReturn(Optional.of(organization));

        var response = organizationService.findById(organizationId);

        assertThat(response).isEqualTo(organization);
        verify(organizationRepository).findById(organizationId);
    }

    @Test
    void shouldFindByIdThrowsNotFoundException() {
        given(organizationRepository.findById(organizationId)).willReturn(Optional.empty());

        assertThatCode(() -> organizationService.findById(organizationId))
                .isInstanceOf(NotFoundException.class);

        verify(organizationRepository).findById(organizationId);
    }

    @Test
    void shouldSaveSuccessfully() {
        var organization = mock(Organization.class);
        given(organizationRepository.save(organization)).willReturn(new Organization());

        var response = organizationService.save(organization);

        assertThat(response).isNotNull();
        verify(organizationRepository).save(organization);
    }

    @Test
    void shouldEditOrganization() {
        var organizationRequest = mock(OrganizationRequest.class);
        var address = AddressRequest.builder()
                .street("Oak Street")
                .number("123")
                .neighborhood("Downtown")
                .city("New York")
                .zipCode("10001")
                .build();
        var organization = Organization.builder()
                .name("Organization name")
                .responsibleDocument("05218776604")
                .description("organization description")
                .instagramUrl("https://instagram.com/organization-name")
                .whatsAppNumber("519823648647")
                .build();

        given(organizationRequest.getAddress()).willReturn(address);
        given(organizationRepository.findById(organizationId)).willReturn(Optional.of(organization));
        given(organizationRepository.save(organizationCaptor.capture())).willReturn(new Organization());

        var response = organizationService.editOrganization(organizationId, organizationRequest);

        assertThat(response).isNotNull();
        verify(organizationRepository).findById(organizationId);
        verify(organizationRepository).save(organizationCaptor.capture());
    }

    @Test
    void shouldDeleteOrganizationSuccessfully() {
        organizationService.deleteOrganization(organizationId);

        verify(organizationRepository).deleteById(organizationId);
    }
}
