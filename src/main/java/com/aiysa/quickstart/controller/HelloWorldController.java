package com.aiysa.quickstart.controller;

import com.aiysa.quickstart.service.HelloService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController

public class HelloWorldController {

    private final HelloService helloService;

    public HelloWorldController(HelloService helloService){
        this.helloService = helloService;

    }
    @GetMapping("/hello")
    public String hello() {
        return helloService.getMessage();
    }
}
