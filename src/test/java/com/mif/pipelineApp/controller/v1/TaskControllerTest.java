package com.mif.pipelineApp.controller.v1;


import com.mif.pipelineApp.configuration.FeatureFlagsProperties;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TaskControllerTest {

    @Mock
    private FeatureFlagsProperties featureFlagsProperties = mock(FeatureFlagsProperties.class);

    private TaskController controller = new TaskController(featureFlagsProperties);

    @Test
    void throws_404_when_foo_disabled() {
        // Given
        given(featureFlagsProperties.getValue("isFooEnabled"))
            .willReturn(false);

        // When, Then
        assertThrows(ResponseStatusException.class, () -> {
            controller.getFoo();
        });
    }

    @Test
    void returns_foo_when_foo_enabled() {
        // Given
        given(featureFlagsProperties.getValue("isFooEnabled"))
            .willReturn(true);

        // When
        String result = controller.getFoo();

        // Then
        assertEquals("FOO", result);
    }

    @Test
    void returns_bar_when_bar_called() {
        // When
        String result = controller.getBar();

        // Then
        assertEquals("BAR", result);
    }

}
