package com.findyourpet.service;

import static com.findyourpet.utils.ImageUtils.ImageUtil.decompressImage;

import com.findyourpet.domain.Image;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.pet.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void uploadImage(Image image) {
        imageRepository.save(image);
    }

    @Transactional
    public byte[] getDecompressedImage(String name) {
        var dbImage = getImageByName(name);
        return decompressImage(dbImage.getImageData());
    }

    public List<Image> getImaqeByPet(String petId) {
        return imageRepository.findByPetId(petId);

    }

    private Image getImageByName(String imageName) {
        return imageRepository.findByName(imageName)
                .orElseThrow(() -> new NotFoundException("resource_not_found", "image"));
    }

}