package com.galaxy.diddao.entity;

import lombok.Data;

/**
 * @Author Ant
 * @Date 2022/10/10
 * @Description: 交易基础字段
 */
@Data
public class TxBase {

    /**
     * `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
     * `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
     * `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
     */

    private Long blockNumber;

    private Long timestamp;

    private String txHash;

}
