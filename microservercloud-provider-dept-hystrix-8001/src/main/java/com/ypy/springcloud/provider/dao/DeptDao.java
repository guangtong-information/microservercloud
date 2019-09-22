package com.ypy.springcloud.provider.dao;

import com.ypy.springcloud.api.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * tk
 * mybatis-plus
 * 自动生成单表的映射！
 */
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();

}
