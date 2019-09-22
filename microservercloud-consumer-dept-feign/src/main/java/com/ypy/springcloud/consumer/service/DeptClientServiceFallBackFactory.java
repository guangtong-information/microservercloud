package com.ypy.springcloud.consumer.service;

import com.ypy.springcloud.api.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {

        return new DeptClientService(){

            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的信息！").setDb_source("no this database in MySql");
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
