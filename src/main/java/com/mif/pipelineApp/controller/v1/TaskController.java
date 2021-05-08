package com.mif.pipelineApp.controller.v1;


import com.mif.pipelineApp.configuration.FeatureFlagsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    FeatureFlagsProperties featureFlagsProperties;

    @GetMapping("/foo")
    public String getFoo() {
        if (featureFlagsProperties.getValue("isFooEnabled")) {
            return "FOO";
        }

        throw new ResponseStatusException(NOT_FOUND);
    }

    @GetMapping("/bar")
    public String getBar() {
        return "BAR";
    }
}
