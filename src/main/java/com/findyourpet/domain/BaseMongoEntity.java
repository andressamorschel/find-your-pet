package com.findyourpet.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseMongoEntity {

    @Id
    private ObjectId id;

    @CreatedDate
    protected LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    private boolean deleted;

    public void setId(String id) {
        Optional.ofNullable(id)
                .ifPresent(newId -> this.id = new ObjectId(newId));
    }

    public String getId() {
        return Optional.ofNullable(id)
                .map(ObjectId::toString)
                .orElse(null);
    }
}
