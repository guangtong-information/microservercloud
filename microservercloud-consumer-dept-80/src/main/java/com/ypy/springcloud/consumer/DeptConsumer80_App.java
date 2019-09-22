package com.ypy.springcloud.consumer;

import com.ypy.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient //开启微服务，从注册中心获取服务并调用
@RibbonClient(name = "MICROSERVERCLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class,args);
    }

}
