package com.findyourpet.dto.response;

import com.findyourpet.domain.enumerated.PetSex;
import com.findyourpet.domain.enumerated.PetSize;
import com.findyourpet.domain.enumerated.PetType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PetResponse {

    private Long id;

    private String color;

    private PetType type;

    private PetSize size;

    private PetSex sex;

    private boolean isNeutered;

    private int age;

    private boolean isNeedATemporaryHome;

    private String description;

    private OrganizationResponse organizationDetails;

    private List<ImageResponse> images;
}
