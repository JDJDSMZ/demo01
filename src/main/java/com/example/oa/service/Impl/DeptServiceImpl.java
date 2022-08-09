package com.example.oa.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.mapper.DeptMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void addDept(Dept dept) {
        deptMapper.insert(dept);
    }
}
