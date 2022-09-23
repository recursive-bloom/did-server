package com.galaxy.diddao.service;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @Author Ant
 * @Date 2022/9/20 18:10
 * @Description:
 */
public interface FunctionEventParseService<T> {

    /**
     * 事件数据解析
     */
    T eventParse(TransactionReceipt transactionReceipt);

    /**
     * 数据插入
     */
    void insertOrUpdateData(T t);
}
