package com.rs.findyourpet.converter;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.utils.ImageUtils.ImageUtil;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageConverter {

    public static Image fromMultipartFile(MultipartFile file) throws IOException {
        return Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
    }
}
