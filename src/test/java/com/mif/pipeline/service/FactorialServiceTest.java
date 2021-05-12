package com.mif.pipeline.service;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialServiceTest {

    FactorialService factorialService = new FactorialService();

    @Test
    void calculates_factorial_of_5() {
        // Given, When
        BigInteger factorial = factorialService.calculateFactorial(5);
        // Then
        assertEquals(BigInteger.valueOf(120), factorial);
    }

    @Test
    void calculates_factorial_of_0() {
        // Given, When
        BigInteger factorial = factorialService.calculateFactorial(0);
        // Then
        assertEquals(BigInteger.valueOf(1), factorial);
    }

    @Test
    void calculates_factorial_of_1() {
        // Given, When
        BigInteger factorial = factorialService.calculateFactorial(1);
        // Then
        assertEquals(BigInteger.valueOf(1), factorial);
    }

}
