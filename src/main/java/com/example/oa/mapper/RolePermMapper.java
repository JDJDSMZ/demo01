package com.example.oa.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RolePermMapper {

    //增加权限中间表
    @Insert("INSERT INTO rbac_role_perm VALUES(#{roleId},#{PermId})")
    void insertRolePerm(@Param("roleId") int roleId, @Param("PermId") int PermId);

    //修改权限中间表(先删除后增加
    @Delete("DELETE FROM rbac_role_perm WHERE roleid = #{roleId}")
    void updateRolePerm(int roleId);
}
