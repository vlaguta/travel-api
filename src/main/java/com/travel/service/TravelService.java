package com.travel.service;

import com.travel.controller.dto.TravelCreateRequest;
import com.travel.controller.dto.TravelDto;

import java.util.List;

public interface TravelService {

    TravelDto get(long id, double lat, double lon);

    List<TravelDto> getAll(double lat, double lon);

    void create(TravelCreateRequest travelRecordsCreateRequest);

    void update(long id, TravelDto travelRecordDto);

    void delete(long id);
}
