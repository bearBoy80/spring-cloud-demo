package com.github.bearboy.biz;

import com.netflix.servo.DefaultMonitorRegistry;
import com.netflix.servo.monitor.Monitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collection;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class BizWebApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BizWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Collection<Monitor<?>> collection = DefaultMonitorRegistry.getInstance().getRegisteredMonitors();
        for (Monitor monitor : collection){
            System.out.println(monitor.getConfig().getName()+ ":" + monitor.getValue());
        }
    }
}
