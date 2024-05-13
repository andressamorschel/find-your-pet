package com.rs.findyourpet.dto.request;

import com.rs.findyourpet.domain.enumerated.PetSex;
import com.rs.findyourpet.domain.enumerated.PetSize;
import com.rs.findyourpet.domain.enumerated.PetType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PetRequest { // TODO: add validations

    private String color;

    @NotNull(message = "")
    private PetType type;

    @NotNull(message = "")
    private PetSize size;

    @NotNull(message = "")
    private PetSex sex;

    private boolean isNeutered;

    private String description;
}
