package com.travel;

import com.travel.configuration.WeatherClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherClientProperties.class)
public class TravelStorageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelStorageApiApplication.class, args);
    }
}
