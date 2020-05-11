package loadbalance;
/**
 * 注意位置：不要放到springboot @ComponentScan 扫描的的包或者子包中
 *
 * */

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定制负载均衡算法
 *
 * */
@Configuration
public class LoadBalanceRule {


    //默认 ：轮询
//    @Bean
    public IRule test(){
        return new RoundRobinRule();
        /**
         * 2020-05-11 23:03:57.465  INFO 9812 --- [p-nio-80-exec-3] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8001
         * 2020-05-11 23:03:57.666  INFO 9812 --- [p-nio-80-exec-4] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:03:57.671  INFO 9812 --- [p-nio-80-exec-4] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         * 2020-05-11 23:03:57.868  INFO 9812 --- [p-nio-80-exec-5] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:03:57.871  INFO 9812 --- [p-nio-80-exec-5] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8001
         * 2020-05-11 23:03:58.050  INFO 9812 --- [p-nio-80-exec-6] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:03:58.053  INFO 9812 --- [p-nio-80-exec-6] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         * 2020-05-11 23:03:58.226  INFO 9812 --- [p-nio-80-exec-7] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:03:58.229  INFO 9812 --- [p-nio-80-exec-7] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8001
         * */

    }

    //随机
//    @Bean
    public IRule randomRule(){
        return new RandomRule();
        /**
         * com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8001
         * 2020-05-11 23:02:34.723  INFO 8064 --- [p-nio-80-exec-3] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:02:34.726  INFO 8064 --- [p-nio-80-exec-3] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         * 2020-05-11 23:02:34.867  INFO 8064 --- [p-nio-80-exec-1] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:02:34.872  INFO 8064 --- [p-nio-80-exec-1] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8001
         * 2020-05-11 23:02:35.203  INFO 8064 --- [p-nio-80-exec-4] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:02:35.206  INFO 8064 --- [p-nio-80-exec-4] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         * 2020-05-11 23:02:35.635  INFO 8064 --- [p-nio-80-exec-5] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:02:35.638  INFO 8064 --- [p-nio-80-exec-5] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         * 2020-05-11 23:02:36.188  INFO 8064 --- [p-nio-80-exec-6] com.pipichao.controller.OrderController  : 远程调用支付接口。。。
         * 2020-05-11 23:02:36.191  INFO 8064 --- [p-nio-80-exec-6] com.pipichao.controller.OrderController  : 服务提供方返回结果：支付成功:8002
         *
         * */
    }


    /**
     * 还有其他的负载均衡算法
     * */

}
