package com.travel.client;

import com.travel.configuration.WeatherClientProperties;
import com.travel.controller.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class YandexWeatherClient {

    private static final String HEADERS_KEY = "X-Yandex-API-Key";
    private static final String HEADERS_VALUE = "43701c57-6916-4a8a-8a6c-67e750475e72";

    @Autowired
    private WeatherClientProperties weatherClientProperties;

    public WeatherDto get(double lat, double lon) {

        RestTemplate restTemplate = new RestTemplate();

        String stringLat = String.valueOf(lat);
        String stringLon = String.valueOf(lon);
        String url = weatherClientProperties.getUrl() + "?lat=" + stringLat + "&lon=" + stringLon;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers.set(HEADERS_KEY, HEADERS_VALUE);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<WeatherDto> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, WeatherDto.class);

        return response.getBody();
    }
}
