package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.pojo.Emp;
import com.example.oa.pojo.Menu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface EmpMapper extends BaseMapper<Emp> {

    //查询所有员工
    @Select("select * from oa_emp")
    @Results(id="roleResultMap",value={
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "photo", property = "photo"),
            @Result(column = "entryDate", property = "entryDate"),
            @Result(column = "salary", property = "salary"),
            @Result(column = "nop", property = "nop"),
            @Result(column = "dept_id", property = "dept.id"),
            @Result(column = "name", property = "dept.name")
    })
    public List<Emp> findByAllEmp();

    //根据部门id查询所有员工信息
    @Select("SELECT * FROM oa_emp where dept_id = #{id}")
    Emp findDeptEmp(int id);
    //根据员工id查询部门所有员工信息
    @Select("SELECT * FROM oa_emp where id = #{id}")
    Emp findIdByEmp(int id);
    //根据id获取部门性别人数
    @Select("SELECT d.id, e.gender, e.dept_id edid,  COUNT(*) AS nop FROM oa_emp e INNER JOIN oa_dept d ON e.dept_id = d.id AND d.id = #{dId} GROUP BY e.gender")
    public List<Emp> listGp(int dId);

}
