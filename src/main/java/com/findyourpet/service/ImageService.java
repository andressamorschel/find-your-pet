package com.findyourpet.service;

import static com.findyourpet.utils.ImageUtils.ImageUtil.decompressImage;

import com.findyourpet.domain.Image;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void uploadImage(Image image) {
        imageRepository.save(image);
    }

    @Transactional
    public byte[] getDecompressedImage(String name) {
        var dbImage = getImaqe(name);
        return decompressImage(dbImage.getImageData());
    }

    private Image getImaqe(String imageName) {
        return imageRepository.findByName(imageName)
                .orElseThrow(() -> new NotFoundException("resource_not_found", "image"));
    }

}