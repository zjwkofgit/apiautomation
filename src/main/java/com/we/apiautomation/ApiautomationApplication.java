package com.we.apiautomation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.we.apiautomation.apitest.dao")
public class ApiautomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiautomationApplication.class, args);
    }

}
