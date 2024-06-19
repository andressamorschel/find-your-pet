package com.findyourpet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address { // TODO: use records

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String zipCode;

    private Organization organization;
}
