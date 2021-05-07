package com.mif.pipelineApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SamplePipelineApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(2, Integer.sum(1, 0));
	}

}
