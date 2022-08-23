package com.example.oa.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.example.oa.pojo.JWtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Autowired
    private JWtProperties jWtProperties;
    public String createJWT(int id, String username, int roleId){
        //获取当前时间
        DateTime now = DateTime.now();
        //调整日期和时间 second 秒  返回偏移调整后的新DateTime
        DateTime newTime = now.offsetNew(DateField.SECOND, jWtProperties.getExpireSeconds());

        //将数据存入map中
        Map<String,Object> payload = new HashMap<String,Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间,这个过期时间必须要大于签发时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷 用户信息
        payload.put("id", id);
        payload.put("username", username);
        payload.put("roleId", roleId);
        //String key = "aabb";
        //String key = "976eff9c06594ac89e52bf86b49ec4f1";

        //payload – 荷载信息 key – HS256(HmacSHA256)密钥
        String token = JWTUtil.createToken(payload, jWtProperties.getSecret().getBytes());
//        System.out.println("token: "+token);
        return token;
    }

    public static void main(String[] args) {
        JwtUtil util=new JwtUtil();
    }

    public boolean verifyJWT(String jwt){
        //解析JWT Token
        JWT token = JWTUtil.parseToken(jwt);
        //校验是否成功
        boolean verifyKey = token.setKey(jWtProperties.getSecret().getBytes()).verify();

 /*       //获取载荷信息
        System.out.println(jwt.getPayload("account"));
        //设置密钥，默认算法是：HS25
        boolean verifyKey = jwt.setKey(jWtProperties.getSecret().getBytes()).verify();
        System.out.println(verifyKey);

        //验证JWT是否有效
        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);*/

        return verifyKey;
    }

    //传入存入的用户载荷信息 获取字符串数据
    public Object getInfo(String jwt, String name){
        JWT token = JWTUtil.parseToken(jwt);

        //强转避免空
        return token.getPayload(name);
    }
}
