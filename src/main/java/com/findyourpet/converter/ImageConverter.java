package com.findyourpet.converter;

import com.findyourpet.domain.Image;
import com.findyourpet.dto.response.ImageResponse;
import com.findyourpet.utils.ImageUtils.ImageUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageConverter {

    public static Image fromMultipartFile(MultipartFile file, String petId) throws IOException {
        return Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .petId(petId)
                .imageData(ImageUtil.compressImage(file.getBytes()))
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
