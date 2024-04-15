package com.github.bearboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class springCloudGateWayDemo {
    public static void main(String[] args) {
        SpringApplication.run(springCloudGateWayDemo.class,args);
    }
}