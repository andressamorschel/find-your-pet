package com.findyourpet.converter;

import com.findyourpet.domain.Address;
import com.findyourpet.dto.response.AddressResponse;
import com.findyourpet.dto.request.AddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public static Address fromAddressRequest(AddressRequest request) {
        return Address.builder()
                .city(request.getCity())
                .neighborhood(request.getNeighborhood())
                .number(request.getNumber())
                .street(request.getStreet())
                .zipCode(request.getZipCode())
                .build();
    }

    public static AddressResponse fromAddressToResponse(Address address) {
        return AddressResponse.builder()
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .number(address.getNumber())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();
    }
}
