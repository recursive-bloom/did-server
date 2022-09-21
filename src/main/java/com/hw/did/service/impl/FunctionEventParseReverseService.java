package com.hw.did.service.impl;

import com.hw.did.abi.Abi;
import com.hw.did.dto.BlockTxDetailInfo;
import com.hw.did.dto.Logs;
import com.hw.did.service.FunctionEventParseService;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeReference;

/**
 * @Author leyangjie
 * @Date 2022/9/21 7:22
 * @Description:
 */
@Service
public class FunctionEventParseReverseService implements FunctionEventParseService {

    @Override
    public void eventParse(BlockTxDetailInfo blockTxDetailInfo) {
        final Logs logList = blockTxDetailInfo.getLogs().get(0);

        final String mainAddress = logList.getTopics().get(1);
        final String node = logList.getTopics().get(2);

  /*      final Type type = FunctionReturnDecoder.decodeIndexedValue(mainAddress, new TypeReference<Address>() {
        });
        System.out.println(type.getValue());
        System.out.println(1);*/

  /*   final Address address = TypeDecoder.decodeStaticStruct(mainAddress.substring(64),0, new TypeReference<Address>() {
        });*/

        final String data = logList.getData();


        final Abi.Node node1 = TypeDecoder.decodeStaticStruct(data, 0, new TypeReference<Abi.Node>() {
        });

        System.out.println(node1);


//        final String data = blockTxDetailInfo.getLogs().get(0).getData();


        System.out.println();
    }


    @Override
    public void insertData() {

    }
}
