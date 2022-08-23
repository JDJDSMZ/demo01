package com.example.oa.web;

import cn.hutool.jwt.JWTException;
import com.example.oa.pojo.Menu;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.MenuService;
import com.example.oa.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController  //复合注解，有Controller及ResponBody
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private JwtUtil jwtUtil;

    //返回list 放到树上
    @GetMapping(value="/list")
    public ResponseEntity<List<Menu>> list(){
        //查找出所有状态为1的菜单
        List<Menu> menus = menuService.findMenuState();

        //将父子菜单分开存放
        //父菜单容器
        List<Menu> pMenus = new ArrayList<>();
        //循环
        for (Menu userMenu : menus) {
            if (userMenu.getPMenu() == null) {
                pMenus.add(userMenu);
                //子菜单集合
                ArrayList<Menu> sunMenus = new ArrayList<>();
                for (Menu menu : menus) {
                    if (menu.getPMenu() != null) {
                        if (menu.getPMenu().equals(userMenu.getId())) {
                            sunMenus.add(menu);
                        }
                    }
                }
                //将子菜单放进父对象中
                userMenu.setSubMenu(sunMenus);
            }
        }
        return new ResponseEntity<>("200", "查询所有状态为1的菜单信息", pMenus);
    }
    //根据jwt解析得到用户id
    @GetMapping("/getusermenus")
    public ResponseEntity<List<Menu>> listUserMenus(HttpServletRequest request){
        //获取jwt请求头
        String jwt = request.getHeader("jwt");
        log.debug("jwt: {}", jwt);
        try {
        //校验jwt
        boolean b = jwtUtil.verifyJWT(jwt);

        } catch (JWTException e) {
            return new ResponseEntity<>("501", "无效令牌或令牌已失效");
        }
        if (jwtUtil.verifyJWT(jwt)){
            int roleId = Integer.parseInt(jwtUtil.getInfo(jwt, "roleId").toString());
            //调用service层
            //根据角色id查找用户菜单
            List<Menu> userMenus = menuService.findByRoleId(roleId);
            if (userMenus.size() == 0){
                return new ResponseEntity<>("400", "没有查询到任何数据");
            }else {
                //将父子菜单分开存放
                //父菜单容器
                List<Menu> pMenus = new ArrayList<>();
                //循环
                for (Menu userMenu : userMenus) {
                    if (userMenu.getPMenu() == null) {
                        pMenus.add(userMenu);
                        //子菜单集合
                        ArrayList<Menu> sunMenus = new ArrayList<>();
                        for (Menu menu : userMenus) {
                            if (menu.getPMenu() != null) {
                                if (menu.getPMenu().equals(userMenu.getId())) {
                                    sunMenus.add(menu);
                                }
                            }
                        }
                        //将子菜单放进父对象中
                        userMenu.setSubMenu(sunMenus);
                    }
                }
                return new ResponseEntity<>("200", "我是响应信息", pMenus);
            }
        }else {
            return new ResponseEntity<>("501", "无效令牌或令牌已失效");
        }
    }
}
