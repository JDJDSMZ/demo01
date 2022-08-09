package com.example.oa.pojo;

import lombok.*;

import java.util.List;

@Setter //set
@Getter //get
@ToString // toString
@AllArgsConstructor //全参构造函数
@NoArgsConstructor // 空参构造函数
public class Role {
    private int id;
    private String name; // 名称
    private String description; // 描述
    private String state; // 状态
    private List<Menu> menus;
    private List<Perm> perms;
}
