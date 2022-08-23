package com.example.oa.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("oa_dept") //告诉mybatis-plus,数据库表名叫什么
public class Dept {
    @TableId(type = IdType.AUTO) //id类型自动,指定主键产生策略
    private Integer id;
    //@TableField("name") // 指定列的对应关系 如果字段名和类名对不上,可以加这个注释
    private String name; //部门名称
    private Integer nop; //部门人数
    private List<Emp> ems; //部门成员
}
