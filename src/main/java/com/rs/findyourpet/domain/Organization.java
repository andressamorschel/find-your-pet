package com.rs.findyourpet.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "organization_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "responsible_document")
    private String responsibleDocument;

    @Column(name = "description")
    private String description;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "whatsapp_number")
    private String whatsAppNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "organization")
    private List<Pet> pets;
}