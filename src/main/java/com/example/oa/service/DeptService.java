package com.example.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oa.pojo.Dept;

import java.util.List;

public interface DeptService extends IService<Dept> {
    // 增加部门
    public void addDept(Dept dept);

    //获取部门人数 生成饼图
    List<Dept> listNp();

    //查询所有部门
public List<Dept> findByAllDept();
}
