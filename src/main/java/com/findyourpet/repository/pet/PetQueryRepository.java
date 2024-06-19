package com.findyourpet.repository.pet;

import com.findyourpet.domain.Pet;
import com.findyourpet.domain.PetQuery;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PetQueryRepository {

    private final MongoOperations mongoOperations;

    public List<Pet> findPets(PetQuery query) {
        var criteria = new Criteria();

        Optional.ofNullable(query.getOrganizationId())
                .ifPresent(organizationId -> criteria.and("organizationId").is(organizationId));

        Optional.ofNullable(query.getColor())
                .ifPresent(color -> criteria.and("color").is(color));

        Optional.ofNullable(query.getCity())
                .ifPresent(city -> criteria.and("city").is(city));

        Optional.ofNullable(query.getPetType())
                .ifPresent(petType -> criteria.and("petType").is(petType));

        Optional.ofNullable(query.getSex())
                .ifPresent(sex -> criteria.and("sex").is(sex));

        Optional.ofNullable(query.getSize())
                .ifPresent(size -> criteria.and("size").is(size));

        return mongoOperations.find(new Query(criteria), Pet.class);
    }
}
