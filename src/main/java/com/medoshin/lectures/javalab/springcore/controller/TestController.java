package com.medoshin.lectures.javalab.springcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(path = "/for-mvc")
    public String getSmth() {
        return "something";
    }
}
