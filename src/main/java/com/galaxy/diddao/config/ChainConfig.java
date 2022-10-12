package com.galaxy.diddao.config;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.constant.SysConfigConstant;
import com.galaxy.diddao.entity.SysConfig;
import com.galaxy.diddao.mapper.SysConfigMapper;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Ant
 * @Date 2022/9/17 23:45
 * @Description:
 */
@Data
@Component
public class ChainConfig implements InitializingBean {

    private String blockReqHost;

    @Value(("${jsonrpc.id}"))
    private String jsonRpcId;

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        final LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.<SysConfig>lambdaQuery()
                .eq(SysConfig::getConfigKey, SysConfigConstant.BLOCK_REQ_HOST);
        final SysConfig sysConfig = sysConfigMapper.selectOne(queryWrapper);
        Assert.notNull(sysConfig, "sys_config表未配置区块链请求host");
        blockReqHost = sysConfig.getConfigValue().trim();
    }
}
