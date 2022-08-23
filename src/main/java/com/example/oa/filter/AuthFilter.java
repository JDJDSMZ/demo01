package com.example.oa.filter;

import com.example.oa.mapper.PermMapper;
import com.example.oa.pojo.Perm;
import com.example.oa.pojo.ResponseEntity;
import com.example.oa.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 权限控制过滤器
 */
public class AuthFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PermMapper permMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //从jwt中获取用户信息，获取该用户拥有的权限
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        System.out.println("________uri :" + uri);
        if (uri.equals("/login") || uri.equals("/menu/getusermenus")) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = req.getHeader("jwt");
        int roleId = Integer.parseInt(jwtUtil.getInfo(jwt, "roleId").toString());
        List<Perm> userPerms = permMapper.findByRoleId(roleId);
        //访问的资源所需要的权限
//        String uri = req.getRequestURI();  //资源
        //判断资源所需要的权限是否包含在用户拥有的权限列表中
        for (Perm userPerm : userPerms) {
            System.out.println(">>>gurl: " + userPerm.getUrl());
            if (userPerm.getUrl() != null){
                if (userPerm.getUrl().equals(uri)) {
                    System.out.println("gurl: " + userPerm.getUrl());
                    System.out.println("uri: "+ uri);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new ResponseEntity<>("501", "用户无权限"));
        response.setContentType("application/json");
        response.getWriter().print(json);
        response.getWriter().close();

    }
}
