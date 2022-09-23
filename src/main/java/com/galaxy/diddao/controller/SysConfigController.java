package com.galaxy.diddao.controller;

import com.galaxy.diddao.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ant
 * @Date 2022/9/17 15:40
 * @Description:
 */
@RestController
public class SysConfigController {

    @Autowired
    private SysConfigMapper sysConfigMapper;


    @GetMapping("getSysConfig")
    public Object get(){
        return sysConfigMapper.selectList(null);
    }
}
