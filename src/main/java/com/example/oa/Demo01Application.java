package com.example.oa;

import com.example.oa.filter.AuthFilter;
import com.example.oa.filter.JwtFilter;
import com.example.oa.filter.OaCorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // 默认会扫描当前主程序所在包及子包
@MapperScan(basePackages = "com.example.oa.mapper")
public class Demo01Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

    //过滤器
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new OaCorsFilter());
        //添加过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //设置过滤器顺序 int最小数字
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return filterRegistrationBean;
    }
    //
//    @Bean
//    public JwtFilter jwtFilter(){
//        return new JwtFilter();
//    }
//    @Bean
//    public FilterRegistrationBean myJwtRegistrationBean(JwtFilter jwtFilter){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(jwtFilter);
//        //添加过滤路径
//        filterRegistrationBean.addUrlPatterns("/*");
//        //设置过滤器顺序 int最小数字
//        filterRegistrationBean.setOrder(Integer.MIN_VALUE + 1);
//        return filterRegistrationBean;
//    }

    //
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }
    @Bean
    public FilterRegistrationBean myAuthFilterRegistrationBean(AuthFilter authFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(authFilter);
        //添加过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //设置过滤器顺序 int最小数字
        filterRegistrationBean.setOrder(Integer.MIN_VALUE + 2);
        return filterRegistrationBean;
    }

}
