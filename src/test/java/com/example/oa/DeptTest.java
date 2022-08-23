package com.example.oa;

import com.example.oa.mapper.DeptMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.pojo.Emp;
import com.example.oa.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DeptTest {
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;



    //获取部门人数
    //iter
    @Test
    void test01() {
        List<Dept> depts = deptMapper.listNp();
        for (Dept dept : depts) {
            System.out.println(dept);
        }
    }
}
