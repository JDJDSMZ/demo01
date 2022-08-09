package com.example.oa.web;

import com.example.oa.pojo.Menu;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController  //复合注解，有Controller及ResponBody
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //返回list 放到树上
    @GetMapping(value="/list")
    public ResponseEntity<List<Menu>> list(){
        //查找出所有状态为1的菜单
        return new ResponseEntity<>("200", "我是响应信息", menuService.findMenuState());
    }
}
