package com.example.oa.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("oa_emp") //告诉mybatis-plus,数据库表名叫什么
public class Emp {
    @TableId(type = IdType.AUTO) //id类型自动,指定主键产生策略
    private Integer id;
    private String name;
    private String gender; //性别
    private String photo; // 相片
    //@TableField("entry_date") // 指定列的对应关系 可以自动转换
    private String entryDate; //入职日期
    private BigDecimal salary; //月薪
    @TableField(value = "dept_id", property = "dept.id") // 指定列的对应关系
    private Dept dept; //部门
    private Integer nop;
}
