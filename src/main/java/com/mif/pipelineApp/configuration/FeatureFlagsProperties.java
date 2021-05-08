package com.mif.pipelineApp.configuration;

import com.configcat.ConfigCatClient;
import com.configcat.PollingModes;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeatureFlagsProperties {

    private ConfigCatClient configCatClient;

    private ConfigCatConfigurationProperties properties;

    public FeatureFlagsProperties(ConfigCatConfigurationProperties properties) {
        this.properties = properties;
        if (Boolean.FALSE.equals(properties.getTurnedOff())) {
            configCatClient = ConfigCatClient.newBuilder()
                .mode(PollingModes.AutoPoll(properties.getInterval()))
                .build(properties.getKey());
        }
    }

    public Boolean getValue(String flag) {
        return Optional.ofNullable(configCatClient)
            .map(client -> client.getValue(Boolean.class, flag, false))
            .orElse(properties.getFlags().getOrDefault(flag, false));
    }
}
