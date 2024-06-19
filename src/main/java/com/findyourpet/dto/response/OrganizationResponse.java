package com.findyourpet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {

    private String id;

    private String name;

    private String responsibleDocument;

    private String description;

    private String instagramUrl;

    private String whatsAppNumber;

    private AddressResponse address;

}
