package com.example.oa;

import com.example.oa.group.AddGroup;
import com.example.oa.pojo.Role;
import com.example.oa.pojo.User;
import com.example.oa.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
public class RoleTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void test01() {
        //
        Role role = new Role();
        role.setName("角色Test");
        role.setDescription("描述有3个权限,2个父菜单,5个子菜单");
        role.setState("1");

        //增加权限中间表
        int[] permIds = {1,2,3};
        int[] menuIds = {1, 2, 3, 4, 6, 7, 8};

        //调用service层方法
        roleService.addRole(role, permIds, menuIds);
    }
}
