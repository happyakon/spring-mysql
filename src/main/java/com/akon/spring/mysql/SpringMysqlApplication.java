package com.akon.spring.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.akon.spring.mysql.model")

public class SpringMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMysqlApplication.class, args);
    }

}
