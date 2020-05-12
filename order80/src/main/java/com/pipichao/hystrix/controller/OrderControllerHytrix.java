package com.pipichao.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pipichao.hystrix.service.OrderServiceHystrix;
import com.pipichao.hystrix.service.OrderServiceHystrixFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderControllerHytrix {
    @Autowired
    private OrderServiceHystrix orderServiceHystrix;

    @RequestMapping("payOrder/hystrix/ok")
    public String payOrder(){
        log.info("开启断路器后的feign调用");
        String result= orderServiceHystrix.payOrderHystrixOk();
        log.info(result);
        return result;
    }
    @RequestMapping("payOrder/hystrix/timeout")
    public String payOrderTimeout(){
        log.info("开启断路器后的feign调用");
        String result= orderServiceHystrix.payOrderTimeout();
        log.info(result);
        return result;
    }


}
