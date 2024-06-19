package com.findyourpet.service;

import static com.findyourpet.domain.enumerated.PetSex.FEMALE;
import static com.findyourpet.domain.enumerated.PetSize.MEDIUM;
import static com.findyourpet.domain.enumerated.PetType.CAT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.findyourpet.domain.Pet;
import com.findyourpet.domain.PetQuery;
import com.findyourpet.dto.request.PetRequest;
import com.findyourpet.exceptions.NotFoundException;
import com.findyourpet.repository.PetRepository;
import com.findyourpet.repository.pet.PetQueryRepository;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
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

    @Mock
    private PetQueryRepository petQueryRepository;

    @InjectMocks
    private PetService petService;

    @Captor
    private ArgumentCaptor<Pet> petCaptor;

    private String petId;

    @BeforeEach
    public void setUp() {
        petId = new ObjectId().toString();
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
        var petQuery = mock(PetQuery.class);

        given(petQueryRepository.findPets(petQuery)).willReturn(List.of(new Pet()));

        var response = petService.findPets(petQuery);

        assertThat(response).isNotEmpty();
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
                .age(10)
                .build();
        given(petRepository.findById(petId)).willReturn(Optional.of(new Pet()));
        given(petRepository.save(petCaptor.capture())).willReturn(new Pet());

        var response = petService.editPet(petId, petRequest);

        assertThat(response).isNotNull();
        verify(petRepository).findById(petId);
        verify(petRepository).save(petCaptor.capture());

        var captorValue = petCaptor.getValue();

        assertThat(captorValue.getAge()).isEqualTo(10);
        assertThat(captorValue.getSize()).isEqualTo(MEDIUM);
        assertThat(captorValue.getSex()).isEqualTo(FEMALE);
        assertThat(captorValue.getType()).isEqualTo(CAT);
        assertThat(captorValue.isNeutered()).isTrue();
        assertThat(captorValue.getDescription()).isEqualTo("white cat");
        assertThat(captorValue.getColor()).isEqualTo("white");
    }
}
