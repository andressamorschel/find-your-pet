package com.rs.findyourpet.service;

import static com.rs.findyourpet.utils.ImageUtils.ImageUtil.decompressImage;

import com.rs.findyourpet.domain.Image;
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
    public byte[] getImage(String name) {
        var dbImage = imageRepository.findByName(name);
        var image = decompressImage(dbImage.get().getImageData());
        return image;
    }

}