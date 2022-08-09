package com.example.oa.vo;

import com.example.oa.pojo.Role;
import lombok.*;

@Setter
@Getter
@ToString // toString
@AllArgsConstructor //全参构造函数
@NoArgsConstructor // 空参构造函数
public class RoleVo {
    private Role role;
    private int[] menuIds;
    private int[] permIds;
}
