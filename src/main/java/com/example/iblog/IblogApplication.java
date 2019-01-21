package com.example.iblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.iblog.dao")
public class IblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(IblogApplication.class, args);
    }

}

