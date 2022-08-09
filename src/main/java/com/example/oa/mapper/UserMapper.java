package com.example.oa.mapper;

import com.example.oa.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface UserMapper {

    //新增用户表
    @Result(column = "roleid", property = "roleId")
    @Insert("insert into rbac_user values (null, #{username}, #{password}, #{roleId}, '1')")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addUser(User user);


    //修改用户
    @UpdateProvider(type = RoleSqlProvider.class, method = "updateUser")
    void updateUser(User user);

    //查询所有用户信息
    @Select("select u.*, r.id roleid, r.name from rbac_user u,rbac_role r where u.roleid=r.id")
    @ResultMap("roleResultMap")
    List<User> findAllUser();

    //登录
    //通过查找用户和密码返回 user和 role
   // @Select("SELECT * FROM rbac_user u WHERE username = #{username} AND PASSWORD = #{password}")
    @Select("SELECT u.*, r.name, r.id roleid FROM rbac_user u, rbac_role r WHERE u.roleid = r.id AND username = #{username} AND PASSWORD = #{password}")
    @ResultMap("roleResultMap")
    User findUnameAndpsw(@Param("username") String username, @Param("password") String password);

    //
    @Select("select u.*,r.name from rbac_user u,rbac_role r where u.role_id=r.id and u.id=#{id}")
    @Results(id="roleResultMap",value={
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "roleid", property = "roleId"),
            @Result(column = "status", property = "status"),
            @Result(column = "roleid", property = "role.id"),
            @Result(column = "name", property = "role.name")
    })
    User getById(int id);

    //删除
    //通过id删除用户
    @Delete("DELETE FROM rbac_user WHERE id = #{id}")
    void deleteUserById(int id);

    //多条件查询
    @SelectProvider(type = RoleSqlProvider.class, method = "findUserByCdn")
    List<User> findUserByCdn(User user);

    public static class RoleSqlProvider {
        public String findUserByCdn(User user){
            //创建动态SQL语句
            String sql = new SQL(){
                {
                    SELECT("*");
                    FROM("rbac_user");
                    if (user.getUsername() != null){
                        WHERE("username like concat('%',concat(#{username},'%'))");
                       // WHERE("username like concat('%',concat(#{state},'%'))");
                    }
                    if(user.getState() != null){
                        WHERE("state like concat('%',concat(#{state},'%'))");
                    }
                    if (user.getRoleId() != 0){
                        WHERE("roleid = #{roleId}");
                    }
                }
            }.toString();
            System.out.println(sql);
            return sql;
        }
        public String updateUser(User user) {
            String sql = new SQL(){
                {
                    UPDATE("rbac_user");
                    if (user.getUsername() != null){
                        SET("username=#{username}");
                    }
                    if(user.getPassword() != null){
                        SET("password=#{password}");
                    }
                    if(user.getRoleId() != 0){
                        SET("roleid=#{roleId}");
                    }
                    if (user.getState() != null){
                        SET("state=#{state}");
                    }
                    WHERE("id=#{id}");
                }
            }.toString();
            System.out.println(sql);
            return sql;
        }
    }
}
