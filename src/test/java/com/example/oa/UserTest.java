package com.example.oa;

import com.example.oa.group.AddGroup;
import com.example.oa.pojo.User;
import com.example.oa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    //登录
    @Test
    public void test02() {
        //用户名密码
        String username = "tom";
        String password = "kk";

        User user = userService.login(username, password);
        System.out.println(user);
    }
    @Test
    public void test01() {
        //通过构造器获取 validator对象
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("  ");

        //校验User类中的属性是否满足校验条件
        Set<ConstraintViolation<User>> validate = validator.validate(user, AddGroup.class);
        //validate.forEach(System.out::println);
        validate.forEach(c->{
            System.out.println(c.getMessage());
        });
    }
}
