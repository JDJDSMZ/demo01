package com.example.oa.service;

import com.example.oa.pojo.Perm;

import java.util.List;

public interface PermService {
    //查询
    List<Perm> getByAllPerm();
    //删除(逻辑
    void deletePermById(int id);
    //查询正常状态下的权限 为1时
    List<Perm> findPermState();
    //根据角色id获取所有用户权限
    List<Perm> findByRoleId(int id);
}
