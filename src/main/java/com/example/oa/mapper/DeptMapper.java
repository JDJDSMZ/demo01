package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.pojo.Dept;
import com.example.oa.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {

    @Select("select * from oa_dept")
    public List<Dept> findByAllDept();

    //获取部门人数
    @Select("SELECT d.*, e.id eid, e.name, e.gender, e.dept_id edid,  COUNT(*) AS nop FROM oa_emp e INNER JOIN oa_dept d ON e.dept_id = d.id GROUP BY d.id")
    @Results(id="deptAndEmpMap",value={
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "nop", property = "nop"),
            @Result(column = "edid", property = "ems",
                    many = @Many(select ="com.example.oa.mapper.EmpMapper.findDeptEmp"), javaType=List.class)
    })
    public List<Dept> listNp();

    @Select("select * ")
    List<Dept> fromAllDept();
}
