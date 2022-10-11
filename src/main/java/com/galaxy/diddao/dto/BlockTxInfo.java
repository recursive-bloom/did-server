package com.galaxy.diddao.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/18 15:46
 * @Description: 块的交易信息
 */
@Data
public class BlockTxInfo implements Serializable {
    private static final long serialVersionUID = -874754260902230674L;

    /**
     * 交易hash
     */
    private String hash;

    /**
     * 目的地址
     */
    private String to;

    /**
     * 时间戳
     */
    private Long timestamp;
}
