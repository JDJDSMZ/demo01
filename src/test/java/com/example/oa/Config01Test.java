package com.example.oa;

import cn.hutool.core.codec.Morse;
import com.example.oa.configBean.Bean01;
import com.example.oa.configBean.ConfigBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

@SpringBootTest
public class Config01Test {
    @Test
    void testMorse() {
        final Morse morseCoder = new Morse();
        String text = "Hello World!";
        String encode = morseCoder.encode(text);
        System.out.println(encode);
    }

    @Test
    void testUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
    }
    @Test
    void test01() {
        ApplicationContext context=new AnnotationConfigApplicationContext(ConfigBean.class);

        //
        Bean01 bean01 = context.getBean(Bean01.class);
    }
}
