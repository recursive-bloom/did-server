package com.galaxy.diddao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author leyangjie
 * @Date 2022/9/17 23:45
 * @Description:
 */
@Data
@Component
public class BlockConfig {

    @Value("${block.req.host}")
    private String blockReqHost;

    @Value(("${block.id}"))
    private String blockId;

}
