package com.example.oa.pojo;


import com.example.oa.group.AddGroup;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    @NotNull(message = "用户名不能为空", groups = AddGroup.class)
    @NotBlank(message = "用户名不能为空字符串")
    @Size(min = 5,max = 10,message = "账号长度必须在5-10之间")
    private String username; //用户名
    private String password; //密码
    private int roleId; //角色
    private String state; //状态
    private Role role;
}
