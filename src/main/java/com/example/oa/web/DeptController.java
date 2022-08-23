package com.example.oa.web;

import com.example.oa.pojo.Dept;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //获取部门列表
    @GetMapping("/list")
    public ResponseEntity<List<Dept>> list(){
        List<Dept> depts = deptService.findByAllDept();

        return new ResponseEntity("200", "返回部门对象", depts);
    }
    //获取部门人数 生成饼图
    @GetMapping("/lstnp")
    public ResponseEntity<List<Dept>> listNp(){
        //调用servlet
        List<Dept> depts = deptService.listNp();
        ArrayList<Map<String,String>> list = new ArrayList<>();
        //封装数据
        for (Dept dept : depts) {
            HashMap<String, String> map = new HashMap<>();
            //System.out.println(dept);
            map.put("value",dept.getNop().toString());
            map.put("name",dept.getName());
            list.add(map);
        }
        return new ResponseEntity("200", "返回部门对象", list);
    }
    //
}
