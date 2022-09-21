package com.hw.did.service.impl;

import com.hw.did.abi.Abi;
import com.hw.did.service.FunctionEventParseService;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import java.util.List;

/**
 * @Author leyangjie
 * @Date 2022/9/21 7:22
 * @Description:
 */
@Service
public class FunctionEventParseReverseService implements FunctionEventParseService {

    @Override
    public void eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);
        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.NODEINFOUPDATED_EVENT, log);
        final List<Type> indexedValues = eventValues.getIndexedValues();
        final Type type = indexedValues.get(1);
        System.out.println(type.getValue().toString());

        System.out.println(1);
    }


    @Override
    public void insertData() {

    }
}
