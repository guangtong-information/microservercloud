package com.ypy.springcloud.consumer.cfgbeans;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@SpringBootConfiguration
public class ConfigBean {

    @Bean
    @LoadBalanced //开启客户端的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
