package com.ypy.springcloud.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义Ribbon的均衡策略
 */
@Configuration
public class MySelfRule {

    /**
     * IRule 默认SC实现了7中负载均衡测试
     */
    @Bean
    public IRule myIRule(){
        // RoundRobinRule 轮询负载均衡算法
        return new RetryRule();
    }

}
