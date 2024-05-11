package com.rs.findyourpet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

@Getter
@NoArgsConstructor
public class OrganizationRequest {// TODO: create logo field, add bean validation, add internacionalization

    @NotBlank
    private String name;

    @CPF
    private String responsibleDocument;

    @Size(max = 255)
    private String description;

    @URL
    private String instagramUrl;

    @NumberFormat
    private String whatsAppNumber;

    private AddressRequest address;
}
