package com.hkbu.project7640;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hkbu.project7640.mapper")
public class Project7640Application {

    public static void main(String[] args) {
        SpringApplication.run(Project7640Application.class, args);
    }

}
