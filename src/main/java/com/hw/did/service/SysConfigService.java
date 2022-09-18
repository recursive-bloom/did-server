package com.hw.did.service;

/**
 * @Author leyangjie
 * @Date 2022/9/17 17:31
 * @Description:
 */
public interface SysConfigService {

    /**
     * 根据configKey 获取configValue
     * @param configKey
     * @return
     */
    String getValue(String configKey);

    /**
     * 根据configKey,获取configValue,把值放到redis缓存中
     * @param configKey
     * @return
     */
    String getCacheValue(String configKey);
}
