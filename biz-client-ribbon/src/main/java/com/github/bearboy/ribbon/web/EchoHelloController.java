package com.github.bearboy.ribbon.web;

import com.github.bearboy.ribbon.feign.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoHelloController {
    @Autowired
    private SayHelloService sayHelloService;
    @GetMapping("/echoHello")
    public String echoHello(String message){
        return sayHelloService.sayHello(message);
    }
}
