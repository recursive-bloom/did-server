package com.hw.did.service;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @Author leyangjie
 * @Date 2022/9/20 18:10
 * @Description:
 */
public interface FunctionEventParseService {

    /**
     * 事件数据解析
     */
    void eventParse(TransactionReceipt transactionReceipt);

    /**
     * 数据插入
     */
    void insertData();
}
