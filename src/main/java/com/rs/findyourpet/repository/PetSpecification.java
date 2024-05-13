package com.rs.findyourpet.repository;

import com.rs.findyourpet.domain.Pet;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@Builder
public class PetSpecification implements Specification<Pet> {

    private String city;

    private String petType;

    private String sex;

    private String size;

    private String color;

    @Override
    public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) { // TODO: improve this implementation and add tests
        var cityPredicate = Optional.ofNullable(city)
                .map(cityName -> {
                    var organizationJoin = root.join("organization");
                    var addressJoin = organizationJoin.join("address");

                    return criteriaBuilder.like(addressJoin.get("city"), "%" + cityName + "%");
                })
                .orElse(null);

        var petTypePredicate = Optional.ofNullable(petType)
                .map(type -> equals(criteriaBuilder, root.get("type"), petType))
                .orElse(null);

        var petSexPredicate = Optional.ofNullable(sex)
                .map(type -> equals(criteriaBuilder, root.get("sex"), sex))
                .orElse(null);

        var petSizePredicate = Optional.ofNullable(size)
                .map(type -> equals(criteriaBuilder, root.get("size"), size))
                .orElse(null);

        var petColorPredicate = Optional.ofNullable(color)
                .map(type -> like(criteriaBuilder, root.get("color"), color))
                .orElse(null);

        var predicates = Stream.of(
                        Optional.ofNullable(cityPredicate),
                        Optional.ofNullable(petTypePredicate),
                        Optional.ofNullable(petSexPredicate),
                        Optional.ofNullable(petSizePredicate),
                        Optional.ofNullable(petColorPredicate))
                .flatMap(Optional::stream)
                .toList();

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate equals(CriteriaBuilder cb, Path<Object> field, Object value) {
        return cb.equal(field, value);
    }

    private Predicate like(CriteriaBuilder cb, Path<String> field, String searchTerm) {
        return cb.like(cb.lower(field), "%" + searchTerm.toLowerCase() + "%");
    }
}
