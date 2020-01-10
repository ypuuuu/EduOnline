package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: xcEduService01
 * @description: springboot启动类
 * @author: Mr.Yang
 * @create: 2020-01-07 10:57
 **/
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms") //扫描实体类
@ComponentScan(basePackages = {"com.xuecheng.api"})  //扫描接口
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"})  //本包
@ComponentScan(basePackages = {"com.xuecheng.framework"})  //描接异常处理类
public class ManageCmsApplication {
    //springboot启动后自动扫描该包及其子包

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}
