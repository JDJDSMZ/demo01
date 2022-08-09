package com.example.oa.mapper;

import com.example.oa.pojo.Perm;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermMapper {

    //查询
    @Result(column = "p_perm", property = "pPerm")
    @Select("SELECT * FROM rbac_perm")
    List<Perm> getByAllPerm();

    //删除(逻辑
    @Update("UPDATE rbac_perm SET state = 'd' WHERE id = #{id}")
    void deletePermById(int id);

    //查询正常状态下的菜单 为1时
    @Result(column = "p_perm", property = "pPerm")
    @Select("SELECT * FROM rbac_perm where state = 1")
    List<Perm> findPermState();

    //根据角色id获取所有用户权限
    @Result(column = "p_perm", property = "pPerm")
    @Select("SELECT * FROM rbac_perm p, rbac_role_perm rp WHERE p.id = rp.permid AND rp.roleid = #{id}")
    List<Perm> findByRoleId(int id);
}
