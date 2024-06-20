package com.findyourpet.controller;

import static com.findyourpet.converter.ImageConverter.fromMultipartFile;
import static org.springframework.http.HttpStatus.CREATED;

import com.findyourpet.service.ImageService;
import java.io.IOException;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/{petId}")
    @ResponseStatus(CREATED)
    public void uploadImage(@PathVariable String petId, @RequestParam("image") MultipartFile file) throws IOException {
        var imageToSave = fromMultipartFile(file, petId);

        imageService.uploadImage(imageToSave);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<Void> getPhoto(@PathVariable String id, Model model) {
        var image = imageService.getImageById(id);

        model.addAttribute("title", image.getName());
        model.addAttribute("image", Base64.getEncoder().encodeToString(image.getImageData().getData()));

        return ResponseEntity.ok()
                .build();
    }

}
