package com.mif.pipelineApp.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Component
@ConfigurationProperties("config-cat")
@Validated
@Data
public class ConfigCatConfigurationProperties {
    private Boolean turnedOff;

    private Integer interval;

    private String key;

    private Map<String, Boolean> flags;
}
