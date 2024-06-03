package com.findyourpet.controller;

import static com.findyourpet.converter.ImageConverter.fromMultipartFile;
import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.valueOf;

import com.findyourpet.service.PetService;
import com.findyourpet.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final PetService petService;

    @PostMapping("/{petId}")
    @ResponseStatus(CREATED)
    public void uploadImage(@PathVariable long petId, @RequestParam("image") MultipartFile file) throws IOException {
        var pet = petService.findById(petId);

        var imageToSave = fromMultipartFile(file, pet);

        imageService.uploadImage(imageToSave);
    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable("name") String name) {
        var image = imageService.getDecompressedImage(name);

        return ResponseEntity.status(OK)
                .contentType(valueOf("image/png"))
                .body(image);
    }
}
