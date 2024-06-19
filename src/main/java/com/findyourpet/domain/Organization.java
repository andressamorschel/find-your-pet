package com.findyourpet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document("Organization")
public class Organization extends BaseMongoEntity {

    private String name;

    private String responsibleDocument;

    private String description;

    private String instagramUrl;

    private String whatsAppNumber;

    private Address address;
}