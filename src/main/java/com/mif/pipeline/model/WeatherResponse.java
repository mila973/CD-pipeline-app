package com.mif.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class WeatherResponse {
    private String description;
    private BigDecimal temp;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;
}
