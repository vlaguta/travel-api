package com.travel.service;

import com.travel.client.YandexWeatherClient;
import com.travel.controller.dto.TravelCreateRequest;
import com.travel.controller.dto.TravelDto;
import com.travel.controller.dto.WeatherDto;
import com.travel.entity.TravelEntity;
import com.travel.exception.BusinessException;
import com.travel.repository.TravelRepository;
import com.travel.service.converter.TravelConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;
    private final YandexWeatherClient yandexWeatherClient;

    public TravelDto get(long id, double lat, double lon) {

        log.info("Getting a travel by id:  " + id);

        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("The records of travel not found with id" + id,
                                HttpStatus.NOT_FOUND.value()));
        TravelDto travelDto = TravelConverter.convertTravelEntityToTravelDto(travel);

        try {
            WeatherDto weatherDto = yandexWeatherClient.get(lat, lon);
            travelDto.setWeather(weatherDto);
        } catch (Exception e) {
            log.error("Error getting data from Yandex.Weather", id, e);
            throw new BusinessException("Unable to get weather", HttpStatus.NO_CONTENT.value());
        }

        return travelDto;
    }

    @Override
    public List<TravelDto> getAll(double lat, double lon) {

        log.info("Getting all travels by id");

        WeatherDto weatherDto = yandexWeatherClient.get(lat, lon);

        List<TravelDto> travelsDto = travelRepository.findAll()
                .stream()
                .map(TravelConverter::convertTravelEntityToTravelDto)
                .collect(Collectors.toList());

        try {
            travelsDto.forEach(travelDto -> travelDto.setWeather(weatherDto));
        } catch (Exception e) {
            log.error("Error getting data from Yandex.Weather", e);
            throw new BusinessException("Enable to get weather", HttpStatus.NO_CONTENT.value());
        }

        return travelsDto;
    }

    @Override
    public void create(TravelCreateRequest travelRecordsCreateRequest) {

        log.info("Saving a travel from user: " + travelRecordsCreateRequest.getUsername());

        TravelEntity travelRecord = TravelConverter.convertTravelCreateRequestToTravelEntity(travelRecordsCreateRequest);
        travelRepository.save(travelRecord);
    }

    @Override
    public void update(long id, TravelDto travelDto) {

        log.info("Update a travel by id: " + id);

        TravelEntity travelRecord = travelRepository.findById(id)
                .orElseThrow(() -> new BusinessException("The records of travel not found with id" + id, HttpStatus.NOT_FOUND.value()));

        if (travelDto.getTravelDate() != null) {
            travelRecord.setTravelDate(travelDto.getTravelDate());
        }
        if (travelDto.getDescription() != null) {
            travelRecord.setDescription(travelDto.getDescription());
        }
        if (travelDto.getCountry() != null) {
            travelRecord.setCountry(travelDto.getCountry());
        }
        if (travelDto.getUsername() != null) {
            travelRecord.setUsername(travelDto.getUsername());
        }
        travelRepository.save(travelRecord);
    }

    @Override
    public void delete(long id) {

        log.info("Deleting a travel by id: " + id);

        travelRepository.deleteById(id);
    }
}
