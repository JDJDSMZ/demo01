package com.example.oa.web;

import com.example.oa.pojo.Dept;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //
    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody Dept dept){
        deptService.save(dept);
        ResponseEntity<String> entity = new ResponseEntity<>();
        entity.setCode("200");
        entity.setMsg("我是响应信息");
        //entity.setData("增加没有数据");
        return entity;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Dept>> list(){
        List<Dept> depts = deptService.list();
        ResponseEntity<List<Dept>> entity = new ResponseEntity<>();
        entity.setCode("200");
        entity.setMsg("我是响应信息");
        entity.setData(depts);
        return entity;
    }
}
