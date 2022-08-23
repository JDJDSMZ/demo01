package com.example.oa;


import com.example.oa.mapper.MenuMapper;
import com.example.oa.pojo.Menu;
import com.example.oa.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MenuTest {

    //
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;

    //根据角色id查找用户菜单
    @Test
    public void testFindByRoleId () throws Exception {
        //
        int roleId = 17;
        //根据角色id查找用户菜单
        List<Menu> userMenus = menuService.findByRoleId(roleId);

        //将父子菜单分开存放
        //父菜单容器
        List<Menu> pMenus = new ArrayList<>();

        //循环
        for (Menu userMenu : userMenus) {
            if (userMenu.getPMenu() == null){
                pMenus.add(userMenu);

                //子菜单集合
                ArrayList<Menu> sunMenus = new ArrayList<>();
                for (Menu menu : userMenus) {
                    if (menu.getPMenu() != null){
                        if (menu.getPMenu().equals(userMenu.getId())){
                            sunMenus.add(menu);
                        }
                    }
                }
                //
                userMenu.setSubMenu(sunMenus);
            }
        }
        for (Menu pMenu : pMenus) {
            System.out.println("pMenu: " + pMenu);
            for (Menu getSubMenu : pMenu.getSubMenu()) {
                System.out.println(getSubMenu);
            }
            System.out.println();
        }
    }

    //查询所有
    @Test
    public void testFindAllMenu2 () throws Exception {
        //使用spring容器获取对象
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        //使用getBean获取spring容器中创建的对象
        MenuService menuService = context.getBean("menuServiceImpl", MenuService.class);

        //
        List<Menu> allMenu = menuService.findByRoleId(9);
        for (Menu menu: allMenu) {
            System.out.println(menu);
        }
    }
    //删除(逻辑
    @Test
    public void testDeleteByMenu () throws Exception {
        //
        int id = 3;

        //
        menuMapper.deleteByMenu(id);
    }

    //查询
    @Test
    public void testFindAllMenu () throws Exception {
        //
        List<Menu> list = menuMapper.findAllMenu();
        for(Menu menu : list){
            System.out.println(menu);
        }
    }
}
