package com.ypy.springcloud.api.service;

import com.ypy.springcloud.api.entities.Dept;

import java.util.List;

/**
 * 服务提供者的接口定义
 */
public interface DeptService {

    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
