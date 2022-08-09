package com.example.oa.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {

    //增加菜单中间表
    @Insert("INSERT INTO rbac_role_menu VALUES(#{roleId},#{menuId})")
    void insertRoleMenuPerm(@Param("roleId") int roleId, @Param("menuId") int menuId);

    //修改菜单中间表(先删除后增加
    @Delete("DELETE FROM rbac_role_menu WHERE roleid = #{roleId}")
    void updateRoleMenu(int roleId);
}
