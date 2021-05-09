package com.mif.pipelineApp.controller.v1;

import com.mif.pipelineApp.model.WeatherResponse;
import com.mif.pipelineApp.service.LiveWeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherController {

    @Autowired
    LiveWeatherService liveWeatherService;

    @GetMapping("/v1/weather")
    public WeatherResponse getCurrentWeather(@RequestParam String city) {
        return liveWeatherService.getCurrentWeather(city);
    }
}
