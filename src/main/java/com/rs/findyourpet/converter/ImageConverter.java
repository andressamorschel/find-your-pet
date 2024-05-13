package com.rs.findyourpet.converter;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.dto.response.ImageResponse;
import com.rs.findyourpet.utils.ImageUtils.ImageUtil;
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
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
    }

    public static ImageResponse fromImageToResponse(Image image) {
        return ImageResponse.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(image.getImageData())
                .build();
    }

    public static List<ImageResponse> fromImageToResponse(List<Image> images) {
        return images.stream()
                .map(ImageConverter::fromImageToResponse)
                .toList();
    }
}
