package com.example.oa.web;

import com.example.oa.pojo.ResponseEntity;
import com.example.oa.pojo.User;
import com.example.oa.service.UserService;
import com.example.oa.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController  //复合注解，有Controller及ResponBody

public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletResponse response;

    //转发登录页面
   /* @GetMapping("/login")
    public String login(){
        return "login";
    }*/

    //用户名 密码登录
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        //调用service层
        User user1 = userService.login(user.getUsername(), user.getPassword());
        System.out.println("user: " + user);

        //比较user是否为空
        if(user1 == null){
            return new ResponseEntity<>("500", "您输入的用户名或密码有误!", null);
        }else {
            //认证,带Token
            String jwt = jwtUtil.createJWT(user1.getId(), user1.getUsername(), user1.getRoleId());
            System.out.println(jwt);
            response.addHeader("jwt",jwt);
            response.setHeader("Access-Control-Expose-Headers", "*");
            return new ResponseEntity("200","用户正确", jwt);
        }
    }

    //退出登录
/*    @GetMapping("/login/logOut")
    public String logOut(HttpSession session){
        //删除session里的user对象
        session.removeAttribute("user");
        return login();
    }*/
}
