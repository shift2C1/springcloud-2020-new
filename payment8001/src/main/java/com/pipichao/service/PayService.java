package com.pipichao.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
//配置全局fallback
@DefaultProperties(defaultFallback = "defaultFallBack")
public class PayService {
    @Value("${server.port}")
    private  String port;

    public String payOrder(){

        return "支付成功:"+port;
    }

//    被调用方开启服务降级保护
//    @HystrixCommand(fallbackMethod = "payOrderTimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")//超时时间
//    })
    @HystrixCommand//不指定参数会调用全局fallback，指定参数掉用指定的方法
    public String payOrderFromFeignClientTimeOut(){
//        try {
//            //模仿请求处理时间较长，实现调用超时异常
//            TimeUnit.SECONDS.sleep(5);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int a=1/0;//这里以异常的形式验证调用端的全局异常
        return "open feign time out :"+port;
    }
    //降级策略
    public String payOrderTimeoutHandler(){
        log.info("服务调用超时降级解决方案。。。");


        return "连接超时，稍后再试"+port;
    }
    ///为了不使得代码臃肿，不可能为每个服务方法都写一个fallback，可以设置一个全局 fallback
    public String defaultFallBack(){
        return "全局fallback";
    }
}
