package com.example.oa;

import com.example.oa.mapper.EmpMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.pojo.Emp;
import com.example.oa.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class EmpTest {
    @Autowired
    private EmpService empService;
    @Autowired
    private EmpMapper empMapper;

    //
    ////根据id获取部门性别人数
    @Test
    void test02() {
        int dId = 1;
        List<Emp> emps = empMapper.listGp(dId);
        for (Emp emp : emps) {
            System.out.println(emp);
        }
    }
    //查询所有员工信息
    @Test
    void testFindByAllEmp() {
        List<Emp> list = empService.findByAllEmp();
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    //增加部门
    @Test
    void testInsert() {
        //
        Emp emp = new Emp();
        emp.setName("张三");
        emp.setGender("男");
        emp.setPhoto("/images");
        emp.setEntryDate("2012-11-04");
        emp.setSalary(new BigDecimal("5000.00"));
        Dept dept = new Dept();
        dept.setId(1);
        emp.setDept(dept);
        //调用service层
        empService.addEmp(emp);
    }
}
