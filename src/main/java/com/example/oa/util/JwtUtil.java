package com.example.oa.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 老谭（<a href="http://www.woniuxy.com">蜗牛学苑</a>）
 */
public class JwtUtil {

    @Value("${jwt.secret}")
    private String key;
    @Value("${jwt.expireSeconds}")
    private int second;
    public String createJWT(){
        //获取当前时间
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.SECOND, second);

        Map<String,Object> payload = new HashMap<String,Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("id", "1");
        payload.put("account", "tom");

        //String key = "aabb";
        String token = JWTUtil.createToken(payload, key.getBytes());
//        System.out.println(token);

        return token;
    }



    public static void main(String[] args) {
        JwtUtil util=new JwtUtil();
        System.out.println(util.createJWT());
        util.verifyJWT();
    }

    public boolean verifyJWT(){
        JwtUtil util=new JwtUtil();
//        System.out.println(util.createJWT());
        String token = util.createJWT();
        JWT jwt = JWTUtil.parseToken(token);
        System.out.println(jwt.getPayload("account"));
        boolean verifyKey = jwt.setKey(key.getBytes()).verify();
        System.out.println(verifyKey);

        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);

        return true;
    }
}
