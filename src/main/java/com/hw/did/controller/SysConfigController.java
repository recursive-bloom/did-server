package com.hw.did.controller;

import com.hw.did.entity.SysConfig;
import com.hw.did.mapper.SysConfigMapper;
import com.hw.did.task.SyncEventTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author leyangjie
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
