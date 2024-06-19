package com.findyourpet.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PetQuery {

    private String city;

    private String petType;

    private String sex;

    private String size;

    private String color;

    private String organizationId;
}
