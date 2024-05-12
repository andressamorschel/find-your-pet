package com.rs.findyourpet.dto.request;

import com.rs.findyourpet.domain.enumerated.PetSex;
import com.rs.findyourpet.domain.enumerated.PetSize;
import com.rs.findyourpet.domain.enumerated.PetType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PetRequest { // TODO: add validations

    private String color;

    private PetType type;

    private PetSize size;

    private PetSex sex;

    private boolean isNeutered;

    private String description;
}
