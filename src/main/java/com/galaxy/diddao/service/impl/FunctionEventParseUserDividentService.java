package com.galaxy.diddao.service.impl;

import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.entity.UserDivident;
import com.galaxy.diddao.mapper.UserDividentMapper;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.util.List;

/**
 * @Author Ant
 * @Date 2022/10/11
 * @Description:
 */
@Service
public class FunctionEventParseUserDividentService implements FunctionEventParseService<UserDivident> {

    @Autowired
    private UserDividentMapper userDividentMapper;

    @Override
    public UserDivident eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.USERDIVIDENT_EVENT, log);
        final List<Type> topics = eventValues.getIndexedValues();
        final List<Type> datas = eventValues.getNonIndexedValues();

        UserDivident userDivident = new UserDivident();
        userDivident.setNode(Numeric.toHexString((byte[]) topics.get(0).getValue()));
        userDivident.setOwner(topics.get(1).getValue().toString());
        userDivident.setAmount(datas.get(0).getValue().toString());
        userDivident.setAccumulated(datas.get(1).getValue().toString());

        setTxBasicProperties(transactionReceipt, userDivident);

        userDivident.setDate(userDivident.getTimestamp() / 3600 / 24);
        return userDivident;
    }

    @Override
    public void insertOrUpdateData(UserDivident userDivident) {
        userDividentMapper.insert(userDivident);
    }
}
