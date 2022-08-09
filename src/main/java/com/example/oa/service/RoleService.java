package com.example.oa.service;

import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.Perm;
import com.example.oa.pojo.Role;

import java.util.List;

public interface RoleService {
    //新增角色表
    void addRole(Role role, int[] permIds, int[] menuIds);

    //列表查询
    PageBean<Role> findAllRole(int currentPage);

    //查询所有正常状态角色信息
    List<Role> findMenuState();
    //修改
    void updateRole(Role role, int[] permIds, int[] menuIds);

    //删除(根据id修改state d)
    void deleteRole(int id);

    //根据用户id查找所有用户的菜单 和权限
    Role findRoleMenuPerm(int id);

    //根据角色id查询所有用户权限
    List<Perm> findByPerm(int id);
}
