package com.truongnq.springbootkeycloack.controller;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DebugController {


    @GetMapping("/{test}")
    public String getPlant(@PathVariable @NotNull String test) {
        return "Hello " + test;
    }
}
