package com.galaxy.diddao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Ant
 * @Date 2022/9/17 23:45
 * @Description:
 */
@Data
@Component
public class ChainConfig {

    @Value("${block.req.host}")
    private String blockReqHost;

    @Value(("${jsonrpc.id}"))
    private String jsonRpcId;

}
