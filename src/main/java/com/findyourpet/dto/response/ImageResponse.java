package com.findyourpet.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.Binary;

@Getter
@Builder
public class ImageResponse {

    private String id;

    private String name;

    private String type;

    private Binary imageData;
}
