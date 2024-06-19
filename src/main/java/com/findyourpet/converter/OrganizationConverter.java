package com.findyourpet.converter;

import static com.findyourpet.converter.AddressConverter.fromAddressRequest;
import static com.findyourpet.converter.AddressConverter.fromAddressToResponse;

import com.findyourpet.domain.Organization;
import com.findyourpet.dto.request.OrganizationRequest;
import com.findyourpet.dto.response.OrganizationResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {

    public Organization fromRequest(OrganizationRequest request) {
        return Organization.builder()
                .description(request.getDescription())
                .instagramUrl(request.getInstagramUrl())
                .responsibleDocument(request.getResponsibleDocument())
                .whatsAppNumber(request.getWhatsAppNumber())
                .name(request.getName())
                .address(fromAddressRequest(request.getAddress()))
                .build();
    }

    public OrganizationResponse fromOrganizationToResponse(Organization organization) {
        var address = fromAddressToResponse(organization.getAddress());
        return OrganizationResponse.builder()
                .description(organization.getDescription())
                .id(organization.getId())
                .whatsAppNumber(organization.getWhatsAppNumber())
                .instagramUrl(organization.getInstagramUrl())
                .name(organization.getName())
                .responsibleDocument(organization.getResponsibleDocument())
                .address(address)
                .build();
    }

    public List<OrganizationResponse> fromOrganizationsToResponse(List<Organization> organizations) {
        return organizations.stream()
                .map(this::fromOrganizationToResponse)
                .toList();
    }
}
