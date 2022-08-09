package com.example.oa.configBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean("bb1")
    public Bean01 bean1() {
        return new Bean01();
    }

    @Bean("bb2")
    public Bean02 bean2() {
        Bean02 bean02=new Bean02();
        bean02.setId(1);
        bean02.setBean01(bean1());
        return bean02;
    }

    @Bean
    public Bean02 bean22(Bean01 bean01) {
        Bean02 bean02=new Bean02();
        bean02.setBean01(bean01);
        return bean02;
    }
}
