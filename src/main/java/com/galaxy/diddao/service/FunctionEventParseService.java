package com.galaxy.diddao.service;

import com.galaxy.diddao.dto.MyTransactionReceipt;
import com.galaxy.diddao.entity.TxBase;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @Author Ant
 * @Date 2022/9/20 18:10
 * @Description:
 */
public interface FunctionEventParseService<T extends TxBase> {

    /**
     * 事件数据解析
     */
    T eventParse(TransactionReceipt transactionReceipt);

    /**
     * 数据插入
     */
    void insertOrUpdateData(T t);

    default TxBase setTxBasicProperties(TransactionReceipt transactionReceipt, T txBase) {

        txBase.setBlockNumber(transactionReceipt.getBlockNumber().longValue());
//        txBase.setTimestamp(transactionReceipt.get);
        txBase.setTxHash(transactionReceipt.getTransactionHash());
        txBase.setTimestamp(((MyTransactionReceipt) transactionReceipt).getTimestamp());
        return txBase;
    }
}
