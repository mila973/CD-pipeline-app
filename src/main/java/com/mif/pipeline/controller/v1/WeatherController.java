package com.mif.pipeline.controller.v1;

import com.mif.pipeline.model.WeatherResponse;
import com.mif.pipeline.service.LiveWeatherService;
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
