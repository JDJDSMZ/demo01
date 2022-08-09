package com.example.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oa.pojo.Dept;

public interface DeptService extends IService<Dept> {
    // 增加部门
    public void addDept(Dept dept);

    //查询所有部门

}
