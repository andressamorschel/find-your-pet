package com.rs.findyourpet.service;

import static com.rs.findyourpet.utils.ImageUtils.ImageUtil.decompressImage;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.exceptions.NotFoundException;
import com.rs.findyourpet.repository.ImageRepository;
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
                .orElseThrow(() -> new NotFoundException("400.017", imageName));
    }

}