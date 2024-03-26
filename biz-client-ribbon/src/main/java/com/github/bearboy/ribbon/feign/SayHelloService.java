package com.github.bearboy.ribbon.feign;

import com.github.bearboy.ribbon.configuration.BizWebRibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "biz-web")
@RibbonClient(name = "biz-web",configuration = BizWebRibbonConfiguration.class)
@RestController
public interface SayHelloService {
    @GetMapping("/sayHello")
    String sayHello(@RequestParam(value = "message") String message);
}
