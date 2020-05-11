package com.pipichao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    /**
     * 以服务名的方式调用
     * */
    private final String payUrl="http://cloud-payment-service/payOrder";
    //CLOUD-PAYMENT-SERVICE
    //cloud-payment-service

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/order")
    public String payOrder(){
        log.info("远程调用支付接口。。。");
        String resp = restTemplate.getForObject(payUrl, String.class);
        log.info("服务提供方返回结果："+resp);
        return resp;
    }

    @Bean
    @LoadBalanced//多个同服务名的微服务注册之后得开启负载均衡，否则报错 找不到主机
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }
}
