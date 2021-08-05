package com.travel.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    @JsonProperty("fact")
    private Info info;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {

        private Integer temp;
        @JsonProperty("feels_like")
        private Integer feelLike;
        private String condition;
        @JsonProperty("wind_speed")
        private int windSpeed;
    }
}
