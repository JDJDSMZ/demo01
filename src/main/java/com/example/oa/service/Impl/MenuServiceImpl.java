package com.example.oa.service.Impl;

import com.example.oa.mapper.MenuMapper;
import com.example.oa.pojo.Menu;
import com.example.oa.service.MenuService;
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
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    //查询所有菜单
    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }



    //删除(逻辑
    @Override
    public void deleteByMenu(int id) {
        menuMapper.deleteByMenu(id);
    }

    //查询正常状态下的菜单 为1时
    @Override
    public List<Menu> findMenuState() {

        return menuMapper.findMenuState();
    }

    //根据角色id查找菜单
    @Override
    public List<Menu> findByRoleId(int id) {
        return menuMapper.findByRoleId(id);
    }
}
