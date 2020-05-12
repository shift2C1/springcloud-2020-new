package com.pipichao.feign.controller;

import com.pipichao.feign.service.OrderServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderControllerFeign {
    @Autowired
    private OrderServiceFeign orderServiceFeign;
    @RequestMapping("/feign/payOrder")
    public String getRemoteFeign(){
        log.info("通过openFeign调用微服务。。。");
        String result= orderServiceFeign.payOrderFromFeignClient();
        log.info(result);
        return result;
    }


    @RequestMapping("/feign/payOrder/TimeOut")
    public String getRemoteFeignTimeOut(){
        log.info("通过openFeign调用微服务。。。");
        String result= orderServiceFeign.payOrderFromFeignClientTimeOut();
        /**
         * 8001 的端口请求时间3秒，请求超时，因此异常抛出，open feign 默认超时时间1s
         * */
        /*
            * feign.RetryableException: Read timed out executing GET http://cloud-payment-service/payOrder
                at feign.FeignException.errorExecuting(FeignException.java:213)
                at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:115)
                at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:80)
                at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:103)
                at com.sun.proxy.$Proxy111.payOrderFromFeignClient(Unknown Source)
            *
            * */
        log.info(result);
        return result;
    }




}
