package com.galaxy.diddao.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.constant.RedisCacheKey;
import com.galaxy.diddao.entity.SysConfig;
import com.galaxy.diddao.mapper.SysConfigMapper;
import com.galaxy.diddao.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author leyangjie
 * @Date 2022/9/17 17:31
 * @Description:
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getValue(String configKey) {
        Assert.notBlank(configKey,"configKey is empty");
        final LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers
                .<SysConfig>lambdaQuery()
                .eq(SysConfig::getConfigKey, configKey);
      return   Optional.ofNullable(sysConfigMapper.selectOne(queryWrapper)).map(SysConfig::getConfigValue)
              .map(String::trim)
              .orElse(null);

    }

    @Override
    public String getCacheValue(String configKey) {
        String redisCacheKey = StringUtils.join(RedisCacheKey.SYS_CONFIG_PREFIX,configKey);
        final String redisCacheValue = stringRedisTemplate.opsForValue().get(redisCacheKey);
        if(StringUtils.isNotBlank(redisCacheValue)){
            return redisCacheValue;
        }

        final String value = getValue(configKey);
        stringRedisTemplate.opsForValue().set(redisCacheKey,value,3, TimeUnit.MINUTES);
        return value;
    }
}
