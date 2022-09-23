package com.galaxy.diddao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ant
 * @Date 2022/9/22 14:08
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
