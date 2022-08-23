package com.example.oa.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OaCorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //Origin 该字段也可以设为星号，表示同意任意跨源请求。
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        //服务器支持的所有跨域请求的方法
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //该字段可选，用来指定本次预检请求的有效期，单位为秒
        resp.setHeader("Access-Control-Max-Age", "3600");
        //服务器支持的所有头信息字段
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Expose-Headers", "*");
        //前端编码
        resp.setContentType("text/html;charset=utf-8");
        if ("OPTIONS".equals(req.getMethod())) {
            resp.setStatus(200);
            return;
        }
        System.out.println("过滤器以生效!!!!!!!!");
        chain.doFilter(request, response);
    }
}
