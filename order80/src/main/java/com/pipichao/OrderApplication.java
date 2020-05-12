package com.pipichao;

import loadbalance.LoadBalanceRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication(scanBasePackages = {"com.pipichao.resttemplate"})
@EnableEurekaClient

//指定负载均衡算法:配置类不要放到@ComponentScan 能扫到的包，不然会整个ribbon 客户端都会采用该算法

//name:指定服务名（服务提供方）
//configuration ：指定配置类
@RibbonClient(name = "cloud-payment-service",configuration = LoadBalanceRule.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

}
