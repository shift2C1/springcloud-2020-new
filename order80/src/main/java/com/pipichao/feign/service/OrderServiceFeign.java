package com.pipichao.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
/**
 * value:通过微服务名字进行调用，openfeign 自动集成了 ribbon的负载均衡（区别于之前的restTemplet + ribbon）
 *
 * */
@FeignClient(value = "cloud-payment-service")
public interface OrderServiceFeign {
    @RequestMapping("/payOrder")
    public String payOrderFromFeignClient();
    @RequestMapping("payOrderTimeOut")
    public String payOrderFromFeignClientTimeOut();
}
