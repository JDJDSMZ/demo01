package com.example.oa.pojo;

import lombok.*;

@Setter //set
@Getter //get
@ToString // toString
@AllArgsConstructor //全参构造函数
@NoArgsConstructor // 空参构造函数
public class Perm {
    private int id;
    private String name; // 名称
    private String identity; // 标识
    private Integer pPerm; //父权限id
    private String url; // 资源路径
    private String state; //状态
}
