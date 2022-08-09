package com.example.oa.pojo;

import lombok.*;

import java.util.List;

@Setter //set
@Getter //get
@ToString // toString
@AllArgsConstructor //全参构造函数
@NoArgsConstructor // 空参构造函数
public class Menu {
    private int id;
    private String name;
    private String identity; //标识
    private String link; // 链接
    private String ico; // 图标
    private Integer pMenu; // 父菜单
    private String state; // 状态
    private List<Menu> subMenu; // 子菜单集合
}
