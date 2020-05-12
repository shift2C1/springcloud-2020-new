package com.pipichao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.pipichao.hystrix"})
@EnableEurekaClient
//Hystrix 断路器启动
@EnableHystrix
public class OrderHystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApp.class,args);
    }
}
