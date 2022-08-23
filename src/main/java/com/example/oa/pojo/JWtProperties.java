package com.example.oa.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")//前缀 去找application.yml配置的两个属性
public class JWtProperties {
    private String secret; //秘钥
    private int expireSeconds; //位移多少时间 过期
}

