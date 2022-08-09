package com.example.oa.service;

import com.example.oa.pojo.Menu;

import java.util.List;

public interface MenuService {
    //查询所有菜单
    List<Menu> findAllMenu();

    //删除(逻辑
    void deleteByMenu(int id);

    //查询正常状态下的菜单 为1时
    List<Menu> findMenuState();

    //根据角色id查找菜单
    List<Menu> findByRoleId(int id);
}
