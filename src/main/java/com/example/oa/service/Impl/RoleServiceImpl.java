package com.example.oa.service.Impl;

import com.example.oa.mapper.*;
import com.example.oa.pojo.Menu;
import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.Perm;
import com.example.oa.pojo.Role;
import com.example.oa.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermMapper rolePermMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PermMapper permMapper;

    //新增角色表
    @Override
    @Transactional //事务控制
    public void addRole(Role role, int[] permIds, int[] menuIds) {
        //增加一个角色
        roleMapper.addRole(role);
        int roleId = role.getId();

        //循环增加两个中间表,一个角色有若干权限和菜单
        //增加权限中间表
        for(int permId : permIds){
            rolePermMapper.insertRolePerm(roleId, permId);
        }
        //增加菜单中间表
        for (int menuId : menuIds){
            roleMenuMapper.insertRoleMenuPerm(roleId, menuId);
        }
    }

    //分页展示角色列表
    @Override
    public PageBean<Role> findAllRole(int currentPage) {
        //
        int pageSize = 5;// 每页显示条数

        // 创建PageBean对象
        PageBean<Role> pageBean = new PageBean<>();

        // 分页插件
        //获取所有角色
        Page<Role> p = PageHelper.startPage(currentPage, pageSize);
        List<Role> allRole = roleMapper.findAllRole();

        // 封装数据
        pageBean.setList(allRole);
        pageBean.setCurrentPage(currentPage);// 当前页
        pageBean.setPageSize(p.getPageSize());// 每页5条数据

        // 查询总条数
        pageBean.setCount((int) p.getTotal());

        // 计算出总页数
        pageBean.setPages(p.getPages());

        return pageBean;
    }

    @Override
    public List<Role> findMenuState() {

        return roleMapper.findMenuState();
    }

    //修改角色
    @Override
    public void updateRole(Role role, int[] permIds, int[] menuIds) {
        //修改角色
        roleMapper.updateRole(role);
        int roleId = role.getId();
        rolePermMapper.updateRolePerm(roleId);
        roleMenuMapper.updateRoleMenu(roleId);

        //循环增加两个中间表,一个角色有若干权限和菜单
        //增加权限中间表
        for(int permId : permIds){
            rolePermMapper.insertRolePerm(roleId, permId);
        }
        //增加菜单中间表
        for (int menuId : menuIds){
            roleMenuMapper.insertRoleMenuPerm(roleId, menuId);
        }
    }

    //根据id删除角色
    @Override
    public void deleteRole(int id) {
        roleMapper.deleteRole(id);
    }

    //根据用户id查找所有用户的菜单 和权限
    @Override
    public Role findRoleMenuPerm(int id) {
        //
        Role role = roleMapper.findByRole(id);

        //根据角色id获取菜单
        List<Menu> menus = menuMapper.findByRoleId(id);

        //根据角色id获取权限
        List<Perm> perms = permMapper.findByRoleId(id);

        //
        role.setMenus(menus);
        role.setPerms(perms);

        return role;
    }

    //根据角色id获取权限
    @Override
    public List<Perm> findByPerm(int id) {
        //根据角色id获取权限
        List<Perm> perms = permMapper.findByRoleId(id);
        return perms;
    }
}
