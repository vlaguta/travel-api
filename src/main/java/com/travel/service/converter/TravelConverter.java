package com.travel.service.converter;

import com.travel.controller.dto.TravelCreateRequest;
import com.travel.controller.dto.TravelDto;
import com.travel.entity.TravelEntity;

public class TravelConverter {

    public static TravelDto convertTravelEntityToTravelDto(TravelEntity travelRecord) {

        return TravelDto.builder()
                .username(travelRecord.getUsername())
                .travelDate(travelRecord.getTravelDate())
                .country(travelRecord.getCountry())
                .description(travelRecord.getDescription())
                .build();
    }

    public static TravelEntity convertTravelCreateRequestToTravelEntity(TravelCreateRequest travelCreateRequest) {

        return TravelEntity.builder()
                .username(travelCreateRequest.getUsername())
                .travelDate(travelCreateRequest.getTravelDate())
                .country(travelCreateRequest.getCountry())
                .description(travelCreateRequest.getDescription())
                .build();
    }
}
