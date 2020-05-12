package com.pipichao.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class OrderServiceHystrix {
    @Autowired
    private OrderServiceHystrixFeign orderServiceHystrixFeign;



    public String payOrderHystrixOk(){
        return orderServiceHystrixFeign.payOrderHystrixOk();
    }

//    设置本地自己的服务熔断保护
    @HystrixCommand(fallbackMethod = "payOrderTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String payOrderTimeout(){
        log.info("开启断路器后的feign调用");
        String result= orderServiceHystrixFeign.payOrderHystrixOkTimeOut();
        log.info(result);
        return result;
    }
    public String payOrderTimeoutHandler(){
        log.info("本地服务熔断保护：服务调用超时降级解决方案。。。");


        return "本地服务熔断保护：，连接超时，稍后再试";
    }


}
