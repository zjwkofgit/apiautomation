package com.we.apiautomation.apitest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/debug")
    public String debugTApi(@RequestBody String tApi){
        return "hello-debug";
    }
}
