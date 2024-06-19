package com.findyourpet.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.findyourpet.domain.Image;
import com.findyourpet.repository.pet.ImageRepository;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    @Test
    void shouldUploadImageSuccessfully() {
        var image = Mockito.mock(Image.class);
        given(imageRepository.save(image)).willReturn(new Image());

        assertThatCode(() -> imageService.uploadImage(image))
                .doesNotThrowAnyException();

        verify(imageRepository).save(image);
    }

    @Test
    void shouldGetDecompressedImageSuccessfully() {
        var image = mock(Image.class);
        var imageName = "image-name";
        given(image.getImageData()).willReturn(generateRandomBytes());
        given(imageRepository.findByName(imageName)).willReturn(Optional.of(image));

        assertThatCode(() -> imageService.getDecompressedImage(imageName))
                .doesNotThrowAnyException();

        verify(imageRepository).findByName(imageName);
    }

    private byte[] generateRandomBytes() {
        var bytes = new byte[5];
        new Random().nextBytes(bytes);
        return bytes;
    }

}
