package com.pipichao.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * 通过编码的方式配置网关和微服务的路由
 *
 * */
@Configuration
public class MyRoute {
    @Bean
    public RouteLocator baidu(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("baiduguanwang",
                r->r.path("/tobaidu").uri("http://www.baidu.com")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator test(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("baiduguanwang",
                r->r.path("/tobaidu").uri("http://www.baidu.com")).build();
        return routes.build();
    }
    public void test(){
        //配置时间的时候用
        ZonedDateTime dateTime =ZonedDateTime.now();
        System.out.println(dateTime);
//        2020-05-13T15:27:47.642+08:00[Asia/Shanghai]
    }

    public static void main(String[] args) {
        new MyRoute().test();
    }
}
