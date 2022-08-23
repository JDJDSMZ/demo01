package com.example.oa.web;

import com.example.oa.pojo.Menu;
import com.example.oa.pojo.Perm;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<Perm> perms = permService.findPermState();

        //将父子菜单分开存放
        //父菜单容器
        List<Perm> pPerms = new ArrayList<>();
        //循环
        for (Perm userPerms : perms) {
            if (userPerms.getPPerm() == null) {
                pPerms.add(userPerms);
                //子菜单集合
                ArrayList<Perm> sunPerm = new ArrayList<>();
                for (Perm perm : perms) {
                    if (perm.getPPerm() != null) {
                        if (perm.getPPerm().equals(userPerms.getId())) {
                            sunPerm.add(perm);
                        }
                    }
                }
                //将子菜单放进父对象中
                userPerms.setSubPerm(sunPerm);
            }
        }
        return new ResponseEntity<>("200", "查询所有状态为1的权限信息", pPerms);
    }
}
