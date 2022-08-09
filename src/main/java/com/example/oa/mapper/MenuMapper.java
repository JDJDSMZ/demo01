package com.example.oa.mapper;

import com.example.oa.pojo.Menu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MenuMapper {
    //查询
    @Result(column = "p_menu", property = "pMenu")
    @Select("SELECT * FROM rbac_menu")
    List<Menu> findAllMenu();

    //删除(逻辑
    @Update("UPDATE rbac_menu SET state = 'd' WHERE id = #{id}")
    void deleteByMenu(int id);

    //查询正常状态下的权限 为1时
    @Result(column = "p_menu", property = "pMenu")
    @Select("SELECT * FROM rbac_menu where state = 1")
    List<Menu> findMenuState();

    //根据用户id查找所有用户的菜单
    @Result(column = "p_menu", property = "pMenu")
    @Select("SELECT * FROM rbac_menu where id = #{id}")
    Menu findRoleMenu(int id);

    //根据用户id查找所有用户的菜单2
    @Result(column = "p_menu", property = "pMenu")
    @Select("SELECT * FROM rbac_menu m, rbac_role_menu rm WHERE m.id = rm.menuid AND rm.roleid = #{id}")
    List<Menu> findByRoleId(int id);

}
