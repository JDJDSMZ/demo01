package com.example.oa.service.Impl;

import com.example.oa.mapper.PermMapper;
import com.example.oa.pojo.Perm;
import com.example.oa.service.PermService;
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
public class PermServiceImpl implements PermService {
    @Autowired
    private PermMapper permMapper;

    //查询
    @Override
    public List<Perm> getByAllPerm() {
        List<Perm> allPerm = permMapper.getByAllPerm();
        return allPerm;
    }

    //删除(逻辑
    @Override
    public void deletePermById(int id) {
        permMapper.deletePermById(id);
    }

    //查询正常状态下的权限 为1时
    @Override
    public List<Perm> findPermState() {
        return permMapper.findPermState();
    }

    //根据角色id获取所有用户权限
    @Override
    public List<Perm> findByRoleId(int id) {

        return permMapper.findByRoleId(id);
    }
}
