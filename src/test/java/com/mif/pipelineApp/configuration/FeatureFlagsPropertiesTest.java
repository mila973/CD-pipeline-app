package com.mif.pipelineApp.configuration;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FeatureFlagsPropertiesTest {

    @Test
    void config_cat_client_not_initiated_on_properties_turned_off() {
        // Given
        ConfigCatConfigurationProperties properties = new ConfigCatConfigurationProperties();
        properties.setTurnedOff(true);

        // When
        FeatureFlagsProperties featureFlagsProperties = new FeatureFlagsProperties(properties);

        // Then
        assertNull(featureFlagsProperties.configCatClient);
    }

    @Test
    void config_cat_client_initiated_on_properties_turned_on() {
        // Given
        ConfigCatConfigurationProperties properties = new ConfigCatConfigurationProperties();
        properties.setTurnedOff(false);
        properties.setInterval(300);
        properties.setKey("key");

        // When
        FeatureFlagsProperties featureFlagsProperties = new FeatureFlagsProperties(properties);

        // Then
        assertNotNull(featureFlagsProperties.configCatClient);
        assertEquals(properties.getInterval(), featureFlagsProperties.properties.getInterval());
        assertEquals(properties.getKey(), featureFlagsProperties.properties.getKey());
    }

    @Test
    void retrieve_flag_value_from_configuration_when_config_cat_turned_off() {
        // Given
        ConfigCatConfigurationProperties properties = new ConfigCatConfigurationProperties();
        properties.setTurnedOff(true);
        properties.setFlags(Collections.singletonMap("isEnabled", true));

        // When
        FeatureFlagsProperties featureFlagsProperties = new FeatureFlagsProperties(properties);

        // Then
        assertEquals(true, featureFlagsProperties.getValue("isEnabled"));
    }

}
