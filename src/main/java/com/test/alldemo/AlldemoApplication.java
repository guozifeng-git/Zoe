package com.test.alldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.test.alldemo.mapper")
public class AlldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlldemoApplication.class, args);
    }

}
