package com.pipichao.controller;

import com.pipichao.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayController {
    @Autowired
    private PayService payService;
    @RequestMapping("/payOrder")
    public String payOrder(){

        return payService.payOrder();
    }

    @RequestMapping("payOrderTimeOut")
    public String payOrderFromFeignClientTimeOut(){
        return payService.payOrderFromFeignClientTimeOut();
    }

    /**
     * 服务熔断
     *
     * */
    @RequestMapping(value = "/circuitbreaker/{id}")
    public String circuitbreaker(@PathVariable("id")Integer id){
        return payService.circuitBreaker(id);
    }
}
