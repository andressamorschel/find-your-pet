package com.findyourpet.converter;

import static com.findyourpet.converter.ImageConverter.fromImagesToResponse;
import static com.findyourpet.converter.ImageConverter.fromMultipartFile;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.findyourpet.domain.Image;
import java.io.IOException;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class ImageConverterTest {

    @Test
    void shouldFromMultipartFileSuccessfully() throws IOException {
        var petId = new ObjectId().toString();
        byte[] imageData = new byte[]{1, 2, 3};
        var multipartFile = mock(MultipartFile.class);
        given(multipartFile.getContentType()).willReturn("image/png");
        given(multipartFile.getOriginalFilename()).willReturn("white-cat");
        given(multipartFile.getBytes()).willReturn(imageData);

        var response = fromMultipartFile(multipartFile, petId);

        assertThat(response.getName()).isEqualTo("white-cat");
        assertThat(response.getType()).isEqualTo("image/png");
        assertThat(response.getPetId()).isEqualTo(petId);
    }

    @Test
    void shouldFromImageToResponseSuccessfully() {
        var image = Image.builder()
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
                .name("white-cat")
                .type("image/png")
                .build();

        var response = fromImagesToResponse(List.of(image));

        assertThat(response.get(0).getName()).isEqualTo("white-cat");
        assertThat(response.get(0).getType()).isEqualTo("image/png");
    }

}
