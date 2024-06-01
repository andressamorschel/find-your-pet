package com.rs.findyourpet.converter;

import static com.rs.findyourpet.converter.ImageConverter.fromImagesToResponse;
import static com.rs.findyourpet.converter.ImageConverter.fromMultipartFile;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.rs.findyourpet.domain.Image;
import com.rs.findyourpet.domain.Pet;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class ImageConverterTest {

    @Test
    void shouldFromMultipartFileSuccessfully() throws IOException {
        var pet = mock(Pet.class);
        byte[] imageData = new byte[]{1, 2, 3};
        var multipartFile = mock(MultipartFile.class);
        given(multipartFile.getContentType()).willReturn("image/png");
        given(multipartFile.getOriginalFilename()).willReturn("white-cat");
        given(multipartFile.getBytes()).willReturn(imageData);

        var response = fromMultipartFile(multipartFile, pet);

        assertThat(response.getName()).isEqualTo("white-cat");
        assertThat(response.getType()).isEqualTo("image/png");
        assertThat(response.getPet()).isEqualTo(pet);
    }

    @Test
    void shouldFromImageToResponseSuccessfully() {
        var image = Image.builder()
                .id(3L)
                .name("white-cat")
                .type("image/png")
                .build();

        var response = fromImagesToResponse(image);

        assertThat(response.getName()).isEqualTo("white-cat");
        assertThat(response.getType()).isEqualTo("image/png");
    }

    @Test
    void shouldFromImagesToResponseSuccessfully() {
        var image = Image.builder()
                .id(3L)
                .name("white-cat")
                .type("image/png")
                .build();

        var response = fromImagesToResponse(List.of(image));

        assertThat(response.get(0).getName()).isEqualTo("white-cat");
        assertThat(response.get(0).getType()).isEqualTo("image/png");
    }

}
