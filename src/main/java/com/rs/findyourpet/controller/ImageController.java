package com.rs.findyourpet.controller;

import static com.rs.findyourpet.converter.ImageConverter.fromMultipartFile;
import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.http.MediaType.valueOf;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(fromMultipartFile(file));
    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable("name") String name) {
        var image = imageService.getImage(name);

        return ResponseEntity.status(OK)
                .contentType(valueOf("image/png"))
                .body(image);
    }
}
