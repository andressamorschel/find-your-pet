package com.findyourpet.converter;

import com.findyourpet.domain.PetQuery;
import org.springframework.stereotype.Component;

@Component
public class PetQueryConverter {

    public PetQuery buildPetQuery(String city, String petType, String size, String sex, String color, String organizationId) {
        return PetQuery.builder()
                .city(city)
                .color(color)
                .petType(petType)
                .sex(sex)
                .size(size)
                .organizationId(organizationId)
                .build();
    }
}
