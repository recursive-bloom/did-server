package com.galaxy.diddao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.entity.ReverseRecord;
import com.galaxy.diddao.mapper.ReverseRecordMapper;
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
 * @Author leyangjie
 * @Date 2022/9/21 7:22
 * @Description:
 */
@Service
public class FunctionEventParseReverseService implements FunctionEventParseService<ReverseRecord> {

    @Autowired
    private ReverseRecordMapper reverseRecordMapper;

    @Override
    public ReverseRecord eventParse(TransactionReceipt transactionReceipt) {
        Log log = transactionReceipt.getLogs().get(0);

        final EventValues eventValues = Contract.staticExtractEventParameters(Abi.REVERSERECORDSET_EVENT, log);
        final List<Type> indexedValues = eventValues.getIndexedValues();
        // event ReverseRecordSet(address indexed main_address, bytes32 indexed node);
        String mainAddress = indexedValues.get(0).getValue().toString();
        byte[] nodeBytes = (byte[]) indexedValues.get(1).getValue();
        final String node = Numeric.toHexString(nodeBytes);

        ReverseRecord reverseRecord = new ReverseRecord();
        reverseRecord.setNode(node);
        reverseRecord.setOwner(mainAddress);

        return reverseRecord;
    }

    @Override
    public void insertOrUpdateData(ReverseRecord reverseRecord) {
        final LambdaQueryWrapper<ReverseRecord> queryWrapper = Wrappers.<ReverseRecord>lambdaQuery()
                .eq(ReverseRecord::getOwner, reverseRecord.getOwner());
        if (Objects.nonNull(reverseRecordMapper.selectOne(queryWrapper))) {
            // 修改
            final LambdaUpdateWrapper<ReverseRecord> updateWrapper = Wrappers.<ReverseRecord>lambdaUpdate()
                    .eq(ReverseRecord::getOwner, reverseRecord.getOwner());
            reverseRecordMapper.update(reverseRecord, updateWrapper);
            return;
        }

        // 新增
        reverseRecordMapper.insert(reverseRecord);
    }

    public static void main(String[] args) throws IOException {
        Web3j web3 = Web3j.build(new HttpService("https://ethereum-ropsten-rpc.allthatnode.com"));
    }
}
