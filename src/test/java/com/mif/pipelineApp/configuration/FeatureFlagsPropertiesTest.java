package com.mif.pipelineApp.configuration;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeatureFlagsPropertiesTest {


    @Test
    public void config_cat_client_not_initiated_on_properties_turned_off() {
        // Given
        ConfigCatConfigurationProperties properties = new ConfigCatConfigurationProperties();
        properties.setTurnedOff(true);

        // When
        FeatureFlagsProperties featureFlagsProperties = new FeatureFlagsProperties(properties);

        // Then
        assertNull(featureFlagsProperties.configCatClient);
    }

    @Test
    public void retrieve_flag_value_from_configuration_when_config_cat_turned_off() {
        // Given
        ConfigCatConfigurationProperties properties = new ConfigCatConfigurationProperties();
        properties.setTurnedOff(true);
        properties.setFlags(Collections.singletonMap("isEnabled", true));

        // When
        FeatureFlagsProperties featureFlagsProperties = new FeatureFlagsProperties(properties);

        // Then
        assertEquals(featureFlagsProperties.getValue("isEnabled"), true);
    }

}
