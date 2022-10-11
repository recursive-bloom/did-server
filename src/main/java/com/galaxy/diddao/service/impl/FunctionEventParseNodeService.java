package com.galaxy.diddao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.abi.Abi;
import com.galaxy.diddao.entity.DidNode;
import com.galaxy.diddao.mapper.DidNodeMapper;
import com.galaxy.diddao.service.FunctionEventParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * @Author Ant
 * @Date 2022/9/21 22:24
 * @Description:
 */
@Service
public class FunctionEventParseNodeService implements FunctionEventParseService<DidNode> {

    @Autowired
    private DidNodeMapper didNodeMapper;

    @Override
    public DidNode eventParse(TransactionReceipt transactionReceipt) {
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
        long expire = ((BigInteger) datas.get(2).getValue()).longValue();
        long ttl = ((BigInteger) datas.get(3).getValue()).longValue();
        long transfer = ((BigInteger) datas.get(4).getValue()).longValue();
        String name = (String) datas.get(5).getValue();

        DidNode didNode = new DidNode();
        didNode.setNode(node);
        didNode.setParentNode(parent);
        didNode.setOwner(owner);
        didNode.setExpire(expire);
        didNode.setTtl(ttl);
        didNode.setTransfer(transfer);
        didNode.setName(name);

        // 设置基本属性
        setTxBasicProperties(transactionReceipt, didNode);

        return didNode;
    }

    @Override
    public void insertOrUpdateData(DidNode didNode) {
        final LambdaQueryWrapper<DidNode> queryWrapper = Wrappers.<DidNode>lambdaQuery()
                .eq(DidNode::getNode, didNode.getNode());

        if (Objects.nonNull(didNodeMapper.selectOne(queryWrapper))) {
            final LambdaUpdateWrapper<DidNode> updateWrapper = Wrappers.<DidNode>lambdaUpdate()
                    .eq(DidNode::getNode, didNode.getNode());
            didNodeMapper.update(didNode, updateWrapper);
            return;
        }

        // 新增
        didNodeMapper.insert(didNode);
    }
}
