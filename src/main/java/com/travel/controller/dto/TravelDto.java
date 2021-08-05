package com.travel.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelDto {

    private String username;
    private LocalDate travelDate;
    private String description;
    private String country;
    private WeatherDto weather;
}
