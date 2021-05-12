package com.mif.acceptance;

import com.mif.acceptance.util.ServiceSetupUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

class PipelineTest {
    private static final String PIPELINE_APP_HOST = "http://localhost:8080";

    @BeforeAll
    static void setup() {
        ServiceSetupUtils.waitForService(PIPELINE_APP_HOST + "/actuator/health");
    }

    @Test
    void validate_bar_endpoint_return_bar() {
        when()
            .get(PIPELINE_APP_HOST + "/task/bar")
            .then()
            .statusCode(200)
            .body(equalTo("BAR"));
    }
}
