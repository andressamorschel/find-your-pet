package com.findyourpet.service;

import com.findyourpet.domain.Image;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.pet.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void uploadImage(Image image) {
        imageRepository.save(image);
    }

    public List<Image> getImaqeByPet(String petId) {
        return imageRepository.findByPetId(petId);
    }

    public Image getImageById(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("resource_not_found", "image"));
    }

}