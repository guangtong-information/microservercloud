package com.ypy.springcloud.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ypy.springcloud.api.entities.Dept;
import com.ypy.springcloud.api.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping(value = "/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable Long id){
        Dept dept = deptService.get(id);
        if (dept == null){
            throw new RuntimeException("出现异常了。。。");
        }
        return dept;
    }

    /**
     * 容错处理的方法，当服务端出现异常的时候，提供更加友好的返回值，实现错误处理。
     * 问题：
     * 1、所有的方法，都要加上容错的处理方法，很麻烦
     * 2、业务代码中耦合了容错处理的处理，解耦（想到AOP)
     * @param id
     * @return
     */
    public Dept processHystrix_Get(@PathVariable Long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的信息！").setDb_source("no this database in MySql");
    }
}
