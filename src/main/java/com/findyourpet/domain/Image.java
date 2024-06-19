package com.findyourpet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("PetImage")
public class Image extends BaseMongoEntity {

    private String name;

    private String type;

    private byte[] imageData;

    private String petId;
}
