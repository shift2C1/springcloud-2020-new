package com.pipichao.hystrix.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "cloud-payment-service",fallback = OrderServiceFallback.class)
public interface OrderServiceHystrixFeign {
    @RequestMapping("/payOrder")
    public String payOrderHystrixOk();
    @RequestMapping("/payOrderTimeOut")
    public String payOrderHystrixOkTimeOut();
}
