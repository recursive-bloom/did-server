package com.galaxy.diddao.service.impl;

import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author leyangjie
 * @Date 2022/9/21 22:24
 * @Description:
 */
@Service
public class FunctionEventParseNodeService implements FunctionEventParseService {
    @Override
    public void eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.NODEINFOUPDATED_EVENT, log);
        final List<Type> topics = eventValues.getIndexedValues();
        final List<Type> datas = eventValues.getNonIndexedValues();

        // event NodeInfoUpdated(bytes32 indexed node, bytes32 parent, address owner, uint64 expire, uint64 ttl, uint64 transfer, string name);
        // step1 解析topic
        final String node = Numeric.toHexString((byte[]) topics.get(0).getValue());

        // step2 解析data
        final String parent = Numeric.toHexString((byte[]) datas.get(0).getValue());
        String owner = datas.get(1).getValue().toString();
        BigInteger expire = (BigInteger) datas.get(2).getValue();
        BigInteger ttl = (BigInteger) datas.get(3).getValue();
        BigInteger transfer = (BigInteger) datas.get(4).getValue();
        String name = (String) datas.get(5).getValue();


        System.out.println(1);
    }

    @Override
    public void insertData() {

    }
}
