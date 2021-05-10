package com.mif.pipeline.controller.v1;


import com.mif.pipeline.configuration.FeatureFlagsProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    @Autowired
    FeatureFlagsProperties featureFlagsProperties;

    @GetMapping("/foo")
    public String getFoo() {
        if (Boolean.TRUE.equals(featureFlagsProperties.getValue("isFooEnabled"))) {
            return "FOO";
        }

        throw new ResponseStatusException(NOT_FOUND);
    }

    @GetMapping("/bar")
    public String getBar() {
        return "BAR";
    }
}