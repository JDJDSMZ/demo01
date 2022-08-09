package com.example.oa.web;

import com.example.oa.pojo.Perm;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //复合注解，有Controller及ResponBody
@RequestMapping("/perm")
public class PermController {
    @Autowired
    private PermService permService;

    //返回list 放到树上
    @GetMapping(value="/list")
    public ResponseEntity<List<Perm>> list(){
        //查找出所有状态为1的菜单
        return new ResponseEntity<>("200", "返回所有状态为1的权限", permService.findPermState());
    }
}
