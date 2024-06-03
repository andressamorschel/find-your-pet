package com.findyourpet.converter;

import com.findyourpet.domain.Organization;
import com.findyourpet.dto.request.OrganizationRequest;
import com.findyourpet.dto.response.OrganizationResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {

    public static Organization fromRequest(OrganizationRequest request) {
        return Organization.builder()
                .description(request.getDescription())
                .instagramUrl(request.getInstagramUrl())
                .responsibleDocument(request.getResponsibleDocument())
                .whatsAppNumber(request.getWhatsAppNumber())
                .name(request.getName())
                .address(AddressConverter.fromAddressRequest(request.getAddress()))
                .build();
    }

    public static OrganizationResponse fromOrganizationToResponse(Organization organization) {
        var address = AddressConverter.fromAddressToResponse(organization.getAddress());
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

    public static List<OrganizationResponse> fromOrganizationToResponse(List<Organization> organizations) {
        return organizations.stream()
                .map(OrganizationConverter::fromOrganizationToResponse)
                .toList();
    }
}
