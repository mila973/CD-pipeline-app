package com.mif.pipeline.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.Stream;

@Component
public class FactorialService {

    public BigInteger calculateFactorial(int num) {
        return Stream.iterate (BigInteger.ONE, i -> i.add(BigInteger.ONE))
            .limit(num)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
