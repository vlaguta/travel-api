package com.travel.controller;

import com.travel.controller.dto.TravelCreateRequest;
import com.travel.controller.dto.TravelDto;
import com.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelRecordService;

    @GetMapping("/{id}")
    public TravelDto get(
            @PathVariable long id,
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return travelRecordService.get(id, lat, lon);
    }

    @GetMapping
    public List<TravelDto> getAll(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return travelRecordService.getAll(lat, lon);
    }

    @PostMapping
    public void create(@RequestBody @Valid TravelCreateRequest travelCreateRequest) {
        travelRecordService.create(travelCreateRequest);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable long id,
            @RequestBody TravelDto travelRecordDto
    ) {
        travelRecordService.update(id, travelRecordDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        travelRecordService.delete(id);
    }
}
