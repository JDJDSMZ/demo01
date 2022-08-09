package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.pojo.Emp;
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
            @Result(column = "dept_id", property = "dept.id"),
            @Result(column = "name", property = "dept.name")
    })
    public List<Emp> findByAllEmp();



}
