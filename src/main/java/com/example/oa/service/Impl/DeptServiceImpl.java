package com.example.oa.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.mapper.DeptMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void addDept(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public List<Dept> listNp() {
        //调用mapper
        List<Dept> depts = deptMapper.listNp();

        //获取部门人数
        return depts;
    }

    //获取所有部门列表
    @Override
    public List<Dept> findByAllDept() {
        List<Dept> depts = deptMapper.findByAllDept();
        return depts;
    }
}
