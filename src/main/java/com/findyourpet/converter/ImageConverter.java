package com.findyourpet.converter;

import static org.bson.BsonBinarySubType.BINARY;

import com.findyourpet.domain.Image;
import com.findyourpet.dto.response.ImageResponse;
import java.io.IOException;
import java.util.List;
import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageConverter {

    public static Image fromMultipartFile(MultipartFile file, String petId) throws IOException {
        return Image.builder()
                .petId(petId)
                .type(file.getContentType())
                .name(file.getName())
                .imageData(new Binary(BINARY, file.getBytes()))
                .build();
    }

    public static ImageResponse fromImagesToResponse(Image image) {
        return ImageResponse.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(image.getImageData())
                .id(image.getId())
                .build();
    }

    public static List<ImageResponse> fromImagesToResponse(List<Image> images) {
        return images.stream()
                .map(ImageConverter::fromImagesToResponse)
                .toList();
    }
}
