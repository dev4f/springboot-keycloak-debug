package com.truongnq.springbootkeycloack.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DebugController {


    @GetMapping("/{test}")
    public String getPlant(@PathVariable @NonNull String test) {
        return "Hello " + test;
    }


    @GetMapping("/gen")
    public String gen() {
        return "OK";
    }

    @GetMapping("/clean")
    public String clean() {
        return "OK";
    }

    @GetMapping("/update")
    public String update() {
        return "OK";
    }

}
