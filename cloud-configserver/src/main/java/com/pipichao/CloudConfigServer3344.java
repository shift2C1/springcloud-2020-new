package com.pipichao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 远程配置中心
 *
 * */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class CloudConfigServer3344 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServer3344.class,args);
    }
}
