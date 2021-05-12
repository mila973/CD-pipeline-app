package com.mif.pipeline.controller.v1;


import com.mif.pipeline.configuration.FeatureFlagsProperties;
import com.mif.pipeline.service.FactorialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TaskControllerTest {

    @Mock
    private FeatureFlagsProperties featureFlagsProperties = mock(FeatureFlagsProperties.class);

    @Mock
    private FactorialService factorialService = mock(FactorialService.class);

    private TaskController controller = new TaskController(featureFlagsProperties, factorialService);

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
        // Given, When
        String result = controller.getBar();

        // Then
        assertEquals("BAR", result);
    }

    @Test
    void returns_24_when_factorial_called_with_4() {
        // Given
        BigInteger expectedResult = BigInteger.valueOf(24);
        given(factorialService.calculateFactorial(4))
            .willReturn(expectedResult);
        // When
        BigInteger result = controller.getFactorial(4);
        // Then
        assertEquals(expectedResult, result);
    }

}
