package com.pipichao.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 * 网关全局过滤器
 * 一半用来做鉴权，日志处理等
 *
 * */


@Component
@Slf4j
public class MyFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        /**
         *
         * 一些判断
         * */
        if (username == null){
            log.info("网关过滤失败，返回");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_GATEWAY);
            //不符合规则，退出
            return exchange.getResponse().setComplete();
        }

        //继续交给其他的过滤器
        return chain.filter(exchange);
    }

    /**
     * http://localhost:9527/payOrder?username=1001
     * 正常访问
     *
     *
     * http://localhost:9527/payOrder
     * 异常
     * HTTP ERROR 502
     * */

    @Override
    public int getOrder() {
        //最高级别
        return 0;
    }
}
