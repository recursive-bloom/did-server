package com.galaxy.diddao;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSpringUtil
@MapperScan(basePackages = "com.galaxy.diddao.mapper")
@SpringBootApplication
public class DiddaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiddaoApplication.class, args);
    }

}
