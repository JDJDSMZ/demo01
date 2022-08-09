package com.example.oa.web;

import com.example.oa.pojo.PageBean;
import com.example.oa.pojo.Perm;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.pojo.Role;
import com.example.oa.service.RoleService;
import com.example.oa.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //增加角色 菜单 权限
    @PostMapping(value="/add")
    public ResponseEntity<String> add(@RequestBody RoleVo roleVo){
        //查看接收值
        log.debug("roleVo:{}, {}, {} ",roleVo.getRole(),roleVo.getPermIds(),roleVo.getMenuIds());
        //调用service层
        roleService.addRole(roleVo.getRole(), roleVo.getPermIds(), roleVo.getMenuIds());
        return new ResponseEntity<>("200", "增加角色成功");
    }

    //首页新增角色菜单 页面转发
    @GetMapping("/role_add")
    public String role_add(Model model, HttpSession session){
        return "role_add";
    }

    //角色列表
    @GetMapping(value="/list")
    public ResponseEntity<PageBean<Role>> role_list(Integer currentPage){
        //调用service层
        //分页查询
        System.out.println("list currentPage: " + currentPage);
        PageBean<Role> pageBean = roleService.findAllRole(currentPage == null ? 1 : currentPage);

        //model.addAttribute("pageBean", pageBean);

        return new ResponseEntity<>("200", "角色列表分页查询", pageBean);
    }

    //删除角色(逻辑
    @GetMapping(value="/del")
    public ResponseEntity<Integer> del(Integer roleId, Integer currentPage){
        //调用service层
        //删除角色
        roleService.deleteRole(roleId);
        System.out.println("roleId: "+roleId);

        //重定向
        //attr.addAttribute("currentPage", str);
        return new ResponseEntity<>("200", "返回到删除角色,列表所在页", currentPage);
    }

    //将数据转发到角色修改页面
    @GetMapping(value="/role_change")
    public String role_change(Model model, int id){
        //获取角色id,并将菜单和权限传回去
        Role role = roleService.findRoleMenuPerm(id);

        //
        model.addAttribute("role", role);

        return "role_change";
    }

    //修改角色信息
    @PostMapping(value="/rChange")
    public ResponseEntity<String> rChange(@RequestBody RoleVo roleVo) {
        //调用service层
        roleService.updateRole(roleVo.getRole(), roleVo.getPermIds(), roleVo.getMenuIds());

        return new ResponseEntity<>("200", "修改角色成功");
    }

    //返回list
    @GetMapping(value="/role_list")
    @ResponseBody
    public ResponseEntity<List<Role>> list(){
        //查找出所有状态为1的菜单
        return new ResponseEntity<>("200", "查询角色成功", roleService.findMenuState());
    }
}
