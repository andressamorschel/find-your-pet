package com.rs.findyourpet.service;

import static com.rs.findyourpet.domain.enumerated.PetSex.FEMALE;
import static com.rs.findyourpet.domain.enumerated.PetSize.MEDIUM;
import static com.rs.findyourpet.domain.enumerated.PetType.CAT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.rs.findyourpet.domain.Pet;
import com.rs.findyourpet.dto.request.PetRequest;
import com.rs.findyourpet.exceptions.NotFoundException;
import com.rs.findyourpet.repository.PetRepository;
import com.rs.findyourpet.repository.PetSpecification;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Captor
    private ArgumentCaptor<PetSpecification> petSpecificationCaptor;

    @Captor
    private ArgumentCaptor<Pet> petCaptor;

    private long petId;

    @BeforeEach
    public void setUp() {
        petId = 2L;
    }

    @Test
    void shouldSaveSuccessfully() {
        var pet = mock(Pet.class);
        given(petRepository.save(pet)).willReturn(new Pet());

        var response = petService.save(pet);

        assertThat(response).isNotNull();
        verify(petRepository).save(pet);
    }

    @Test
    void shouldFindPetsSuccessfully() {
        var city = "Porto Alegre";
        var size = "P";
        var petType = "cat";
        var color = "white";
        var sex = "male";

        given(petRepository.findAll(petSpecificationCaptor.capture())).willReturn(List.of(new Pet()));

        var response = petService.findPets(city, petType, size, sex, color);

        assertThat(response).isNotEmpty();
        verify(petRepository).findAll(petSpecificationCaptor.capture());
    }

    @Test
    void shouldFindByIdSuccessfully() {
        given(petRepository.findById(petId)).willReturn(Optional.of(new Pet()));

        var response = petService.findById(petId);

        assertThat(response).isNotNull();
        verify(petRepository).findById(petId);
    }

    @Test
    void shouldFindByIdThrowsNotFoundException() {
        given(petRepository.findById(petId)).willReturn(Optional.empty());

        assertThatCode(() -> petService.findById(petId))
                .isInstanceOf(NotFoundException.class);

        verify(petRepository).findById(petId);
    }

    @Test
    void shouldEditPetSuccessfully() {
        var petRequest = PetRequest.builder()
                .color("white")
                .description("white cat")
                .isNeutered(true)
                .type(CAT)
                .sex(FEMALE)
                .size(MEDIUM)
                .build();
        given(petRepository.findById(petId)).willReturn(Optional.of(new Pet()));
        given(petRepository.save(petCaptor.capture())).willReturn(new Pet());

        var response = petService.editPet(petId, petRequest);

        assertThat(response).isNotNull();
        verify(petRepository).findById(petId);
        verify(petRepository).save(petCaptor.capture());

        var captorValue = petCaptor.getValue();

//        assertThat(captorValue.getAge())
    }
}
