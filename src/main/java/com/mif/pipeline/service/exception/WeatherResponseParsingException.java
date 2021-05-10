package com.mif.pipeline.service.exception;

public class WeatherResponseParsingException extends RuntimeException {
    public WeatherResponseParsingException(String message, Exception e) {
        super(message, e);
    }
}
