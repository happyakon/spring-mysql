package com.akon.spring.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.akon.spring.mysql.model")
@EnableDiscoveryClient
public class SpringMysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMysqlApplication.class, args);
    }

}
