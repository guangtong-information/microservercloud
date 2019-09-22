package com.ypy.springcloud.provider.service;

import com.ypy.springcloud.api.entities.Dept;
import com.ypy.springcloud.api.service.DeptService;
import com.ypy.springcloud.provider.dao.DeptDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

//    @Autowired
    @Resource
    private DeptDao deptDao;

    @Override
    public boolean add(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.findAll();
    }
}
