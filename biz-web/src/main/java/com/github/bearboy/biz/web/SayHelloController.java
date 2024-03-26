package com.github.bearboy.biz.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @GetMapping("/sayHello")
    public String sayHello(String message){
        return "sayHello" + message;
    }
}
