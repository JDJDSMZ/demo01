package com.example.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oa.pojo.Emp;

import java.util.List;

public interface EmpService extends IService<Emp> {
    // 增加员工
    public void addEmp(Emp emp);

    //查询所有员工
    public List<Emp> findByAllEmp();
}
