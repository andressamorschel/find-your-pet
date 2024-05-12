package com.rs.findyourpet.domain;

import static jakarta.persistence.EnumType.STRING;

import com.rs.findyourpet.domain.enumerated.PetSex;
import com.rs.findyourpet.domain.enumerated.PetSize;
import com.rs.findyourpet.domain.enumerated.PetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "pet")
public class Pet {

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
}