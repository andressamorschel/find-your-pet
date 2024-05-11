package com.rs.findyourpet.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressRequest { // TODO: use zip code api

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;
}
