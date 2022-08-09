package com.example.oa.web;

import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.pojo.User;
import com.example.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //用户列表
    @GetMapping(value="/list")
    public ResponseEntity<PageBean<User>> list(Integer currentPage){
        //调用service层
        //分页查询
        System.out.println("list currentPage: " + currentPage);
        PageBean<User> pageBean = userService.findAllUser(currentPage == null ? 1 : currentPage);

        return new ResponseEntity<>("200", "用户列表分页查询", pageBean);
    }

    //转发到增加用户页面
    @GetMapping(value="/add")
    public String add(){
        return "user_add";
    }

    //增加用户
    @PostMapping(value="/add")
    public ResponseEntity<String> add(@RequestBody User user){
        //调用service层
        userService.addUser(user);
        return new ResponseEntity<>("200", "增加用户"+user.getUsername()+"成功");
    }
}
