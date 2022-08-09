package com.example.oa.service.Impl;

import com.example.oa.mapper.UserMapper;
import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.User;
import com.example.oa.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        //新增用户表
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        //修改用户
        userMapper.updateUser(user);
    }

    //查询所有用户信息
    @Override
    public PageBean<User> findAllUser(int currentPage) {
        //
        int pageSize = 5;// 每页显示条数

        // 创建PageBean对象
        PageBean<User> pageBean = new PageBean<>();

        // 分页插件
        //获取所有角色
        Page<User> p = PageHelper.startPage(currentPage, pageSize);
        //查询所有用户信息
        List<User> allUser = userMapper.findAllUser();

        // 封装数据
        pageBean.setList(allUser);
        pageBean.setCurrentPage(currentPage);// 当前页
        pageBean.setPageSize(p.getPageSize());// 每页5条数据

        // 查询总条数
        pageBean.setCount((int) p.getTotal());

        // 计算出总页数
        pageBean.setPages(p.getPages());

        return pageBean;
    }

    @Override
    public User login(String username, String password) {
        //登录
        //通过查找用户和密码返回 user
        User unameAndpsw = userMapper.findUnameAndpsw(username, password);

        return unameAndpsw;
    }

    @Override
    public void deleteUserById(int id) {
        //删除
        //通过id删除用户
        userMapper.deleteUserById(id);
    }

    @Override
    public List<User> findUserByCdn(User user) {
        //多条件查询
        List<User> userList = userMapper.findUserByCdn(user);

        return userList;
    }
}
