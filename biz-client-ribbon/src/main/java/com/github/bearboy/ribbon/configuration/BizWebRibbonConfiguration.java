package com.github.bearboy.ribbon.configuration;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class BizWebRibbonConfiguration {
    @Bean
    IRule ribbonRule() {
        return new WeightUpTimeIRule();
    }
}
