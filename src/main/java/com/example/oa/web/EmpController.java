package com.example.oa.web;

import com.example.oa.pojo.Dept;
import com.example.oa.pojo.Emp;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //根据id获取部门性别人数
    @GetMapping("/lstGp")
    public ResponseEntity<List<Emp>> listGp(int dId){
        //调用servlet
        List<Emp> emps = empService.listGp(dId);
        //
        ArrayList<Map<String,String>> list = new ArrayList<>();
        for (Emp emp : emps) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", emp.getNop().toString());
            map.put("name", emp.getGender());
            list.add(map);
        }
        return new ResponseEntity("200", "返回部门员工对象", list);
    }
}
