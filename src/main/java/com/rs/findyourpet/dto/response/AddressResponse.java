package com.rs.findyourpet.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String zipCode;
}
