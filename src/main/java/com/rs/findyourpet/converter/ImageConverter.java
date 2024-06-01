package com.rs.findyourpet.converter;

import static com.rs.findyourpet.utils.ImageUtils.ImageUtil.compressImage;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.dto.response.ImageResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageConverter {

    public static Image fromMultipartFile(MultipartFile file, Pet pet) throws IOException {
        return Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .pet(pet)
                .imageData(compressImage(file.getBytes()))
                .build();
    }

    public static ImageResponse fromImagesToResponse(Image image) {
        return ImageResponse.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(image.getImageData())
                .build();
    }

    public static List<ImageResponse> fromImagesToResponse(List<Image> images) {
        return images.stream()
                .map(ImageConverter::fromImagesToResponse)
                .toList();
    }
}
