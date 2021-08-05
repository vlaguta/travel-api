package com.travel.service;

import com.travel.client.YandexWeatherClient;
import com.travel.controller.dto.TravelCreateRequest;
import com.travel.controller.dto.TravelDto;
import com.travel.entity.TravelEntity;
import com.travel.exception.BusinessException;
import com.travel.repository.TravelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TravelServiceImpl.class)
public class TravelServiceTest {

    @Autowired
    TravelService travelService;

    @MockBean
    TravelRepository travelRepository;

    @MockBean
    YandexWeatherClient yandexWeatherClient;

    @Test
    void getTravelSuccessfullyTest() {

        //given
        long existedTravelId = 1L;
        double lat = 1.0;
        double lon = 1.0;

        TravelEntity travel = TravelEntity.builder()
                .id(1L)
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        when(travelRepository.findById(existedTravelId))
                .thenReturn(Optional.of(travel));
        //when
        TravelDto actualTravelDto = travelService.get(existedTravelId, lat, lon);

        //then
        TravelDto expectedTravelDto = TravelDto.builder()
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        assertEquals(expectedTravelDto, actualTravelDto);
    }

    @Test
    void getTravelByIdTravelNotFoundTest() {

        //given
        long notExistedTravelId = 1L;
        double lat = 1.0;
        double lon = 1.0;

        TravelEntity travel = TravelEntity.builder()
                .id(1L)
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        when(travelRepository.findById(notExistedTravelId))
                .thenReturn(Optional.empty());
        //when
        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> travelService.get(notExistedTravelId, lat, lon)
        );

        //then
        String expectedErrorMessage = "The records of travel not found with id" + notExistedTravelId;
        assertEquals(expectedErrorMessage, businessException.getMessage());
    }

    @Test
    void getAllTravelsSuccessfullyTest() {

        //given
        double lat = 1.0;
        double lon = 1.0;
        TravelEntity travel = TravelEntity.builder()
                .id(1L)
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        when(travelRepository.findAll())
                .thenReturn(List.of(travel));
        //when
        List<TravelDto> actualTravelsDto = travelService.getAll(lat, lon);

        //then
        TravelDto expectedTravelDto = TravelDto.builder()
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        assertEquals(List.of(expectedTravelDto), actualTravelsDto);
    }

    @Test
    void createTravelSuccessfullyTest() {

        //given
        TravelCreateRequest travelCreateRequest = TravelCreateRequest.builder()
                .username("username")
                .country("country")
                .description("description")
                .travelDate(LocalDate.now())
                .build();

        //when
        travelService.create(travelCreateRequest);

        //then
        TravelEntity expectedTravelEntity = TravelEntity.builder()
                .username("username")
                .country("country")
                .travelDate(LocalDate.now())
                .description("description")
                .build();

        verify(travelRepository).save(expectedTravelEntity);
    }

    @Test
    void updateTravelSuccessfullyTest() {

        //given
        long existedTravelId = 1L;

        TravelDto travelDto = TravelDto.builder()
                .username("updated username")
                .country(" updated country")
                .description("updated description")
                .travelDate(LocalDate.of(1990, 2, 22))
                .build();

        TravelEntity travelEntity = TravelEntity.builder()
                .username(" initial username")
                .country("initial country")
                .travelDate(LocalDate.now())
                .description("initial description")
                .build();

        when(travelRepository.findById(existedTravelId))
                .thenReturn(Optional.of(travelEntity));


        //when
        travelService.update(existedTravelId, travelDto);

        //then
        TravelEntity expectedTravelEntity = TravelEntity.builder()
                .username("updated username")
                .country(" updated country")
                .description("updated description")
                .travelDate(LocalDate.of(1990, 2, 22))
                .build();

        verify(travelRepository).save(expectedTravelEntity);
    }

    @Test
    void updateTravelButTravelNotFoundTest() {

        //given
        long notExistedTravelId = 1L;

        TravelDto travelDto = TravelDto.builder()
                .username("updated username")
                .country(" updated country")
                .description("updated description")
                .travelDate(LocalDate.of(1990, 2, 22))
                .build();

        TravelEntity travelEntity = TravelEntity.builder()
                .username(" initial username")
                .country("initial country")
                .travelDate(LocalDate.now())
                .description("initial description")
                .build();

        when(travelRepository.findById(notExistedTravelId))
                .thenReturn(Optional.empty());


        //when
        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> travelService.update(notExistedTravelId, travelDto)
        );

        //then
        String expectedErrorMessage = "The records of travel not found with id" + notExistedTravelId;
        assertEquals(expectedErrorMessage, businessException.getMessage());
    }

    @Test
    void deleteTravelSuccessfullyTest() {

        //given
        long existedId = 1L;

        //when
        travelService.delete(existedId);

        //then
        verify(travelRepository).deleteById(existedId);
    }
}
