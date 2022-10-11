package com.galaxy.diddao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.entity.DidReverse;
import com.galaxy.diddao.mapper.DidReverseMapper;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

/**
 * @Author Ant
 * @Date 2022/9/21 7:22
 * @Description:
 */
@Service
public class FunctionEventParseReverseService implements FunctionEventParseService<DidReverse> {

    @Autowired
    private DidReverseMapper didReverseMapper;

    @Override
    public DidReverse eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.REVERSERECORDSET_EVENT, log);
        final List<Type> indexedValues = eventValues.getIndexedValues();
        // event ReverseRecordSet(address indexed main_address, bytes32 indexed node);
        String mainAddress = indexedValues.get(0).getValue().toString();
        byte[] nodeBytes = (byte[]) indexedValues.get(1).getValue();
        final String node = Numeric.toHexString(nodeBytes);

        DidReverse didReverse = new DidReverse();
        didReverse.setNode(node);
        didReverse.setOwner(mainAddress);
        setTxBasicProperties(transactionReceipt, didReverse);

        return didReverse;
    }

    @Override
    public void insertOrUpdateData(DidReverse didReverse) {
        final LambdaQueryWrapper<DidReverse> queryWrapper = Wrappers.<DidReverse>lambdaQuery()
                .eq(DidReverse::getOwner, didReverse.getOwner());
        if (Objects.nonNull(didReverseMapper.selectOne(queryWrapper))) {
            // 修改
            final LambdaUpdateWrapper<DidReverse> updateWrapper = Wrappers.<DidReverse>lambdaUpdate()
                    .eq(DidReverse::getOwner, didReverse.getOwner());
            didReverseMapper.update(didReverse, updateWrapper);
            return;
        }

        // 新增
        didReverseMapper.insert(didReverse);
    }

    public static void main(String[] args) throws IOException {
        Web3j web3 = Web3j.build(new HttpService("https://ethereum-ropsten-rpc.allthatnode.com"));
    }
}
