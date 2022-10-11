package com.galaxy.diddao.dto;

import lombok.Data;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @Author Ant
 * @Date 2022/10/11
 * @Description:
 */
@Data
public class MyTransactionReceipt extends TransactionReceipt {
    /**
     * 时间戳
     */
    private Long timestamp;
}
