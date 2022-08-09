package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.pojo.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {

    @Select("select * from oa_dept")
    public List<Dept> findByAllDept();
}
