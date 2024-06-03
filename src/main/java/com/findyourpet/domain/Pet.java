package com.findyourpet.domain;

import static jakarta.persistence.EnumType.STRING;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourpet.domain.enumerated.PetSex;
import com.findyourpet.domain.enumerated.PetSize;
import com.findyourpet.domain.enumerated.PetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "pet")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pet { // TODO: add flag adopted

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Enumerated(STRING)
    @Column(name = "type")
    private PetType type;

    @Enumerated(STRING)
    @Column(name = "size")
    private PetSize size;

    @Enumerated(STRING)
    @Column(name = "sex")
    private PetSex sex;

    @Column(name = "neutered")
    private boolean neutered;

    @Column(name = "need_a_temporary_home")
    private boolean needATemporaryHome;

    @Column(name = "age")
    private int age;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @OneToMany(mappedBy = "pet")
    private List<Image> images;

    @JsonBackReference
    public Organization getOrganization() {
        return organization;
    }
}