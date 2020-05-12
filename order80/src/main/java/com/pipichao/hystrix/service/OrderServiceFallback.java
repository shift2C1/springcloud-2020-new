package com.pipichao.hystrix.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 使用@FeignClient 的fallback 属性进行服务降级
 *
 * 可以看出 ：@FeignClient 的fallback 和@HystrixCommand 同时存在时候，
 * @FeignClient 的fallback 会生效，后者不在执行
 *
 * */
@Slf4j
@Component
public class OrderServiceFallback implements OrderServiceHystrixFeign {
    public String payOrderHystrixOk() {
        log.info("orderServiceFallback...");
        return "orderServiceFallback...";
    }

    public String payOrderHystrixOkTimeOut() {
        log.info("orderServiceFallback...");
        return "orderServiceFallback...";
    }
}
