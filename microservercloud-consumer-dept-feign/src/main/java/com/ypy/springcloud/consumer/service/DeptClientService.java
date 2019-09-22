package com.ypy.springcloud.consumer.service;

import com.ypy.springcloud.api.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 调用部门的服务（8001）
 * 使用Feigin，面向接口的编程，全部采用注解的方式
 *
 * 客户端调用服务的时候，如果出现异常，那么将会调用fallbackFactory定义的容错代码，实现服务的客户端容错处理
 */
@FeignClient(value = "MICROSERVERCLOUD-DEPT",fallbackFactory = DeptClientServiceFallBackFactory.class)
public interface DeptClientService {

    @PostMapping(value = "/dept/add")
    boolean add(@RequestBody Dept dept);

    @GetMapping(value = "/dept/get/{id}")
    Dept get(@PathVariable("id") Long id);

    @GetMapping(value = "/dept/list")
    List<Dept> list();

}
