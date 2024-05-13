package com.rs.findyourpet.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponse {

    private Long id;

    private String name;

    private String type;

    private byte[] imageData;
}
