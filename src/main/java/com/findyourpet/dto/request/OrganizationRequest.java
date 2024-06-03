package com.findyourpet.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@NoArgsConstructor
public class OrganizationRequest {// TODO: create logo field

    @NotBlank(message = "{400.006}")
    private String name;

    @CPF(message = "{400.007}")
    private String responsibleDocument;

    @Size(max = 255, message = "{400.008}")
    private String description;

    @URL(message = "{400.009}")
    private String instagramUrl;

    @Pattern(regexp = "^s*(\\d{2}|\\d{0})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$", message = "{400.010}")
    private String whatsAppNumber;

    @Valid
    @NotNull(message = "{400.011}")
    private AddressRequest address;
}
