package com.example.oa.filter;

import com.example.oa.pojo.ResponseEntity;
import com.example.oa.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getRequestURI().equals("/login")){
            chain.doFilter(request,response);
            return;
        }
        String jwt = req.getHeader("jwt");
        log.debug("JWT:{}", jwt);
        //校验jwt是否有效
        if (jwtUtil.verifyJWT(jwt)) {
            chain.doFilter(request, response);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new ResponseEntity<>("501", "令牌失效"));
            response.setContentType("application/json");
            response.getWriter().print(json);
            response.getWriter().close();
        }
    }
}
