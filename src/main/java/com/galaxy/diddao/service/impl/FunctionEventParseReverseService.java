package com.galaxy.diddao.service.impl;

import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.io.IOException;
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

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.REVERSERECORDSET_EVENT, log);
        final List<Type> indexedValues = eventValues.getIndexedValues();
        // event ReverseRecordSet(address indexed main_address, bytes32 indexed node);
        String mainAddress = indexedValues.get(0).getValue().toString();
        byte[] nodeBytes = (byte[]) indexedValues.get(1).getValue();
        final String node = Numeric.toHexString(nodeBytes);


        System.out.println(1);
    }


    @Override
    public void insertData() {

    }

    public static void main(String[] args) throws IOException {
        Web3j web3 = Web3j.build(new HttpService("https://ethereum-ropsten-rpc.allthatnode.com"));
    }
}
