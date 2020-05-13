package com.pipichao.service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.config.HystrixCommandConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
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

    /**
     * 服务降级
     *
     *
     * */
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


    /**
     * 服务熔断：
     * 原理：在错误窗口期内，如果请求异常的次数和总请求次数的比率超过设置的错误比率阈值，贼hystrix断路器自动跳闸
     *      *
     *      *  一段时间后，再次恢复调用链路
     *
     *  HystrixCommandProperties:每个参数的具体位置所在类
     *
     *
     * */
    @HystrixCommand(
            fallbackMethod = "circuitBreakerFallback",
            commandProperties ={
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//在时间窗口期的请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期：单位毫秒
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//错误请求的失败比率

            }
    )
    public String circuitBreaker(Integer id){
        if (id.intValue()<0){
            //模拟服务器异常
            log.info("参数："+id+"服务器异常");
            throw new RuntimeException("服务器异常");
        }
        log.info("参数："+id+"正常访问");
        return "服务没被熔断。。";
    }
    public String circuitBreakerFallback(Integer id){
        log.info("参数："+id+"服务器异常，被熔断");
        return "服务被熔断了。。。";
    }
    /*
    * 参数：1服务器异常，被熔断
2020-05-13 13:37:02.689  INFO 3472 --- [nio-8001-exec-6] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:03.284  INFO 3472 --- [nio-8001-exec-7] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:03.754  INFO 3472 --- [nio-8001-exec-8] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:04.274  INFO 3472 --- [nio-8001-exec-9] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:04.738  INFO 3472 --- [io-8001-exec-10] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:05.290  INFO 3472 --- [nio-8001-exec-2] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:08.739  INFO 3472 --- [x-PayService-10] com.pipichao.service.PayService          : 参数：-1服务器异常
2020-05-13 13:37:08.740  INFO 3472 --- [x-PayService-10] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:09.914  INFO 3472 --- [nio-8001-exec-1] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:10.330  INFO 3472 --- [nio-8001-exec-5] com.pipichao.service.PayService          : 参数：-1服务器异常，被熔断
2020-05-13 13:37:10.786   : 参数：-1服务器异常，被熔断
2020-05-13 13:37:18.459   : 参数：1服务器异常，被熔断  ：被熔断之后即使正常访问也被降级
2020-05-13 13:37:18.963   : 参数：1正常访问 ：过了一段时间 恢复闭合状态
2020-05-13 13:37:19.485   : 参数：1正常访问
2020-05-13 13:37:20.570   : 参数：1正常访问
    * */



}
