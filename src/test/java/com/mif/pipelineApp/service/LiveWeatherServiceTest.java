package com.mif.pipelineApp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mif.pipelineApp.model.WeatherResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext
class LiveWeatherServiceTest {

    WireMockServer wireMockServer = new WireMockServer(options().port(8090));

    private LiveWeatherService liveWeatherService;

    @BeforeEach
    public void beforeEach() {
        wireMockServer.start();
        wireMockServer.resetAll();
    }

    @AfterEach
    public void afterEach() {
        wireMockServer.stop();
        wireMockServer.resetScenarios();
    }

    @Test
    void converts_string_weather_response_to_weather_response_class() {
        // Given
        liveWeatherService = new LiveWeatherService(
            new RestTemplateBuilder().build(),
            new ObjectMapper()
        );
        WeatherResponse expectedResponse = buildWeatherResponse();

        String liveResponse = getLiveWeatherResponse();
        // When
        WeatherResponse response = liveWeatherService.convert(ResponseEntity.ok(liveResponse));
        // Then
        assertEquals(expectedResponse.getDescription(), response.getDescription());
        assertEquals(expectedResponse.getFeelsLike(), response.getFeelsLike());
        assertEquals(expectedResponse.getTemp(), response.getTemp());
        assertEquals(expectedResponse.getWindSpeed(), response.getWindSpeed());
    }

    @Test
    void get_live_weather_information_from_weather_API_provider() {
        // Given
        liveWeatherService = new LiveWeatherService(
            new RestTemplateBuilder().rootUri("http://localhost:8090").build(),
            new ObjectMapper()
        );
        WeatherResponse expectedResponse = buildWeatherResponse();
        liveWeatherService.apiKey = "apiKey";
        wireMockServer.stubFor(WireMock.get("/data/2.5/weather?q=Vilnius&appid=apiKey&units=metric")
            .willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withStatus(200)
                .withBody(getLiveWeatherResponse()))
        );
        // When
        WeatherResponse response = liveWeatherService.getCurrentWeather("Vilnius");
        // Then
        assertEquals(expectedResponse.getDescription(), response.getDescription());
        assertEquals(expectedResponse.getFeelsLike(), response.getFeelsLike());
        assertEquals(expectedResponse.getTemp(), response.getTemp());
        assertEquals(expectedResponse.getWindSpeed(), response.getWindSpeed());
    }

    private WeatherResponse buildWeatherResponse() {
        return new WeatherResponse("clear sky", BigDecimal.valueOf(284.15), BigDecimal.valueOf(282.61), BigDecimal.valueOf(3.09));
    }

    private String getLiveWeatherResponse() {
        return "{\"coord\":{\"lon\":25.2798,\"lat\":54.6892},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":" +
            "\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":284.15,\"feels_like\":282.61,\"temp_min\":284.15," +
            "\"temp_max\":284.15,\"pressure\":1020,\"humidity\":50},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":150},\"clouds\":{" +
            "\"all\":0},\"dt\":1620584188,\"sys\":{\"type\":1,\"id\":1883,\"country\":\"LT\",\"sunrise\":1620527015,\"sunset\":1620583626}," +
            "\"timezone\":10800,\"id\":593116,\"name\":\"Vilnius\",\"cod\":200}";
    }
}
