package com.mif.pipelineApp.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LiveWeatherRestTemplate {

    @Bean
    public RestTemplate weatherRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .rootUri("http://api.openweathermap.org")
            .build();
    }
}
