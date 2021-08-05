package com.travel.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "weather")
public class WeatherClientProperties {

    private String url;
}
