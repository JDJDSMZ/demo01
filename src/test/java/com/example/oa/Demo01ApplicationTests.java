package com.example.oa;

import com.example.oa.mapper.DeptMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo01ApplicationTests {
    //注入spring bean类
    @Autowired
    private DeptMapper deptMapper; // 爆红是因为这是mybatis-plus 的@MapperScan 扫描进去的, 不是常规spring扫描的

    @Autowired
    private DeptService deptService;

    //增加部门
    @Test
    void testInsert3() {
        Dept dept = new Dept();
        dept.setName("部门4");
        deptService.addDept(dept);
    }

    //增加部门
    @Test
    void testInsert2() {
        Dept dept = new Dept();
        dept.setName("部门3");
        deptService.save(dept);
    }

    //junit5
    //增加部门
    @Test
    void testInsert() {
        Dept dept = new Dept();
        dept.setName("部门2");
        deptMapper.insert(dept);
    }

    //自己写的查询方法
    @Test
    void testFindByAllDept() {
        List<Dept> byAllDept = deptMapper.findByAllDept();
        for (Dept dept : byAllDept) {
            System.out.println(dept);
        }
    }

    //mybatis-plus 自带的查询方法
    @Test
    void testFindByAllDept2() {
        List<Dept> byAllDept = deptMapper.selectList(null);
        for (Dept dept : byAllDept) {
            System.out.println(dept);
        }
    }

}
