package com.example.oa.web;


import cn.hutool.jwt.JWTException;
import com.example.oa.pojo.Menu;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.pojo.Role;
import com.example.oa.pojo.User;
import com.example.oa.service.EmpService;
import com.example.oa.service.MenuService;
import com.example.oa.service.RoleService;
import com.example.oa.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private EmpService empService;
    @Autowired
    private JwtUtil jwtUtil;

    //首页菜单数据展示
    @PostMapping("menu")
    public ResponseEntity<List<Menu>> index(@RequestBody User user){
        //获取Session中的用户ID
        //User user = (User) session.getAttribute("user");
        int roleId = user.getRoleId();
        System.out.println(user);

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
                    //将子菜单放进父对象中
                    userMenu.setSubMenu(sunMenus);
                }
            }
            //将数据转发给页面
            //model.addAttribute("pMenus", pMenus);

            return new ResponseEntity<>("200", "根据roleId返回菜单列表", pMenus);
        }

    }

    //欢迎页面
    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession session){
        return "welcome";
    }
    //根据jwt获取user
    @GetMapping("/getuserName")
    public ResponseEntity<User> getName(HttpServletRequest request){
        //获取请求头里的jwt
        String jwt = request.getHeader("jwt");
        //
        User user = new User();
        try {
            //校验jwt
            boolean b = jwtUtil.verifyJWT(jwt);
            //解析jwt里的信息
            String username = jwtUtil.getInfo(jwt, "username").toString();
            //存入user对象里
            user.setUsername(username);
        } catch (JWTException e) {
            return new ResponseEntity<>("501", "无效令牌或令牌已失效");
        }
        return new ResponseEntity<>("200", "返回user对象", user);
    }
}
