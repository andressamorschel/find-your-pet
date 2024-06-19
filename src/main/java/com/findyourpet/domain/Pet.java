package com.findyourpet.domain;

import com.findyourpet.domain.enumerated.PetSex;
import com.findyourpet.domain.enumerated.PetSize;
import com.findyourpet.domain.enumerated.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("Pet")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pet extends BaseMongoEntity {

    private String color;

    private PetType type;

    private PetSize size;

    private PetSex sex;

    private boolean neutered;

    private boolean needATemporaryHome;

    private int age;

    private String description;

    private String organizationId;
}