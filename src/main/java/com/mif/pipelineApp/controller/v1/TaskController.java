package com.mif.pipelineApp.controller.v1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/foo")
    public String getFoo() {
        return "FOO";
    }
}
