package com.findyourpet.service;

import static com.findyourpet.converter.AddressConverter.fromAddressRequest;

import com.findyourpet.domain.Organization;
import com.findyourpet.dto.request.OrganizationRequest;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.OrganizationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public List<Organization> find() {
        return organizationRepository.findAll();
    }

    public Organization findById(String id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("resource_not_found", "organization"));
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization editOrganization(String organizationId, OrganizationRequest request) {
        var organization = findById(organizationId);

        organization.setId(organizationId);

        var editedOrganization = buildEditedOrganization(organization, request);

        return organizationRepository.save(editedOrganization);
    }

    public void deleteOrganization(String organizationId) {
        organizationRepository.deleteById(organizationId);
    }

    private Organization buildEditedOrganization(Organization organization, OrganizationRequest request) {
        return organization.toBuilder()
                .address(fromAddressRequest(request.getAddress()))
                .description(request.getDescription())
                .instagramUrl(request.getInstagramUrl())
                .responsibleDocument(request.getResponsibleDocument())
                .whatsAppNumber(request.getWhatsAppNumber())
                .name(request.getName())
                .build();
    }
}
