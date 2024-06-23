package com.findyourpet.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "{400.001}")
    private String street;

    @NotBlank(message = "{400.002}")
    private String number;

    @NotBlank(message = "{400.003}")
    private String neighborhood;

    @NotBlank(message = "{400.004}")
    private String city;

    @NotBlank(message = "{400.005}")
    private String zipCode;
}
