package com.mif.pipelineApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mif.pipelineApp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class LiveWeatherService {

    private static final String WEATHER_URL = "/data/2.5/weather?q={city}&appid={key}&units=metric";

    @Value("${open-weather.api-key}")
    protected String apiKey;

    @Autowired
    private final RestTemplate weatherRestTemplate;
    private final ObjectMapper objectMapper;

    public LiveWeatherService(RestTemplate weatherRestTemplate, ObjectMapper objectMapper) {
        this.weatherRestTemplate = weatherRestTemplate;
        this.objectMapper = objectMapper;
    }

    public WeatherResponse getCurrentWeather(String city) {
        String url = new UriTemplate(WEATHER_URL).expand(city, apiKey).normalize().toString();
        ResponseEntity<String> response = weatherRestTemplate.getForEntity(url, String.class);

        return convert(response);
    }


    protected WeatherResponse convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new WeatherResponse(root.path("weather").get(0).path("description").asText(),
                BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
                BigDecimal.valueOf(root.path("wind").path("speed").asDouble()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
