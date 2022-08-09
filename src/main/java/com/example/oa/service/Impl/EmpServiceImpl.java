package com.example.oa.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.mapper.EmpMapper;
import com.example.oa.pojo.Emp;
import com.example.oa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    //注入
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void addEmp(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public List<Emp> findByAllEmp() {
        return empMapper.findByAllEmp();
    }
}
