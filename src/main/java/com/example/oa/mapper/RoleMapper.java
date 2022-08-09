package com.example.oa.mapper;

import com.example.oa.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface RoleMapper {
    //新增角色表
    @Insert("INSERT INTO rbac_role VALUES(NULL, #{name}, #{description}, 1)")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addRole(Role role);

    //列表查询
    @Select("SELECT * FROM rbac_role")
    List<Role> findAllRole();

    //正常状态角色
    @Select("SELECT * FROM rbac_role where state = '1'")
    List<Role>findMenuState();

    //修改
    @UpdateProvider(type = RoleSqlProvider.class, method = "updateRole")
    void updateRole(Role role);

    //删除(根据id修改state d)
    @Update("UPDATE rbac_role SET state = 'd' WHERE id = #{id}")
    void deleteRole(int id);

    //根据用户id查找所有用户的菜单
    @Select("SELECT r.id, r.name, r.description, r.state, m.id mid, m.name, m.identity, m.link, m.ico, m.p_menu, m.state FROM rbac_role r, rbac_role_menu rm, rbac_menu m WHERE r.id = rm.roleid AND m.id = rm.menuid AND r.id = #{id}")
    @Results(id="roleResultMap",value={
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "state", property = "state"),
            @Result(column = "mid", property = "menus",
                    many = @Many(select ="com.rbac.mapper.MenuMapper.findRoleMenu"), javaType=List.class)
    })
    List<Role> findRoleMenu(int id);

    //根据角色id获取角色
    @Select("SELECT * FROM rbac_role WHERE id = #{id}")
    Role findByRole(int id);

    //修改角色方法
    public static class RoleSqlProvider {
        public String updateRole(Role role){
            String sql = new SQL(){
                {
                    UPDATE("rbac_role");
                    if (role.getName() != null){
                        SET("name = #{name}");
                    }
                    if (role.getDescription() != null){
                        SET("description = #{description}");
                    }
                    if (role.getState() != null){
                        SET("state = #{state}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
            System.out.println(sql);
            return  sql;
        }
    }
}
