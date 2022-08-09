package com.example.oa.web;

import com.example.oa.pojo.Emp;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    //
    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody Emp emp){
        empService.save(emp);
        ResponseEntity<String> entity = new ResponseEntity<>();
        entity.setCode("200");
        entity.setMsg("我是响应信息");
        //entity.setData("增加没有数据");
        return entity;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Emp>> list(){
        List<Emp> emps = empService.findByAllEmp();
        ResponseEntity<List<Emp>> entity = new ResponseEntity<>();
        entity.setCode("200");
        entity.setMsg("我是响应信息");
        entity.setData(emps);
        return entity;
    }
}
