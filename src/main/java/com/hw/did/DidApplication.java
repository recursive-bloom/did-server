package com.hw.did;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.hw.did.mapper")
@SpringBootApplication
public class DidApplication {

    public static void main(String[] args) {
        SpringApplication.run(DidApplication.class, args);
    }

}
