package com.galaxy.diddao.service.impl;

import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.entity.PlatformIncome;
import com.galaxy.diddao.mapper.PlatformIncomeMapper;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import java.util.List;

/**
 * @Author Ant
 * @Date 2022/10/11
 * @Description:
 */
@Service
public class FunctionEventParsepPlatformIncomeService implements FunctionEventParseService<PlatformIncome> {

    @Autowired
    private PlatformIncomeMapper platformIncomeMapper;

    @Override
    public PlatformIncome eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.PLATFORMINCOME_EVENT, log);
        final List<Type> topics = eventValues.getIndexedValues();
        final List<Type> datas = eventValues.getNonIndexedValues();

        PlatformIncome platformIncome = new PlatformIncome();
        platformIncome.setItem(topics.get(0).getValue().toString());
        platformIncome.setIncome(datas.get(0).getValue().toString());
        platformIncome.setAccumulated(datas.get(1).getValue().toString());

        setTxBasicProperties(transactionReceipt, platformIncome);
        platformIncome.setDate(platformIncome.getTimestamp() / 3600 / 24);
        return platformIncome;
    }

    @Override
    public void insertOrUpdateData(PlatformIncome platformIncome) {
        platformIncomeMapper.insert(platformIncome);
    }
}
