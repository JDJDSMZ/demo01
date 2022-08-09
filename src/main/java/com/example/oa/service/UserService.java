package com.example.oa.service;

import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.User;

import java.util.List;

public interface UserService {
    //新增用户表
    void addUser(User user);

    //修改用户
    void updateUser(User user);

    //查询所有用户信息
    PageBean<User> findAllUser(int currentPage);

    //登录
    //通过查找用户和密码返回 user
    User login(String username, String password);

    //删除
    //通过id删除用户
    void deleteUserById(int id);

    //多条件查询
    List<User> findUserByCdn(User user);
}
