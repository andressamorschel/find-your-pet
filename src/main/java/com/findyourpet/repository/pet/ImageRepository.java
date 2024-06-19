package com.findyourpet.repository.pet;

import com.findyourpet.domain.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    @Query("{ name: ?0 }")
    Optional<Image> findByName(String name);

    @Query("{ petId: ?0 }")
    List<Image> findByPetId(String petId);
}
