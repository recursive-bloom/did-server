package com.galaxy.diddao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.config.ChainConfig;
import com.galaxy.diddao.entity.DidNode;
import com.galaxy.diddao.entity.ReverseRecord;
import com.galaxy.diddao.exception.BizException;
import com.galaxy.diddao.mapper.DidNodeMapper;
import com.galaxy.diddao.mapper.ReverseRecordMapper;
import com.galaxy.diddao.req.LatestDividentReq;
import com.galaxy.diddao.resp.DidNodeResp;
import com.galaxy.diddao.resp.LatestDividentResp;
import com.galaxy.diddao.service.DidNodeService;
import com.galaxy.diddao.utils.FunctionEncodeUtils;
import com.galaxy.diddao.utils.HttpUtils;
import com.galaxy.diddao.utils.SignUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author Ant
 * @Date 2022/9/22 15:11
 * @Description:
 */
@Slf4j
@Service
public class DidNodeServiceImpl implements DidNodeService {

    @Autowired
    private DidNodeMapper didNodeMapper;

    @Autowired
    private ReverseRecordMapper reverseRecordMapper;

    @Value("${contract.address}")
    private String contractAddress;

    @Autowired
    private ChainConfig chainConfig;

    @Override
    public List<DidNodeResp> getDidNodeListByAddress(String owner) {
        final LambdaQueryWrapper<DidNode> didNodQueryWrapper = Wrappers.<DidNode>lambdaQuery()
                .eq(DidNode::getOwner, owner);

        final List<DidNode> didNodeList = didNodeMapper.selectList(didNodQueryWrapper);
        if (CollectionUtil.isEmpty(didNodeList)) {
            return null;
        }
        final List<DidNodeResp> didNodeRespList = BeanUtil.copyToList(didNodeList, DidNodeResp.class);

        // 设置默认did
        final LambdaQueryWrapper<ReverseRecord> reverseRecordQuery = Wrappers.<ReverseRecord>lambdaQuery().eq(ReverseRecord::getOwner, owner);
        final ReverseRecord reverseRecord = reverseRecordMapper.selectOne(reverseRecordQuery);
        if (Objects.nonNull(reverseRecord)) {
            for (DidNodeResp didNodeResp : didNodeRespList) {
                if (StringUtils.equals(didNodeResp.getNode(), reverseRecord.getNode())) {
                    didNodeResp.setIsDefaultDidNode(Boolean.TRUE);
                    break;
                }
            }
        }

        return didNodeRespList;
    }

    @Override
    public LatestDividentResp getLatestDivident(LatestDividentReq req) {
        // step1 通过签名和内容解析出地址
        List<String> recoverAddressList = SignUtils.getRecoverAddressFromSignature(req.getSignature(), req.getNode(),
                SignUtils.SIGN_NODE_PREFIX);

        LambdaQueryWrapper<DidNode> didNodeQueryWrapper = Wrappers.<DidNode>lambdaQuery().eq(DidNode::getNode, req.getNode());
        DidNode didNode = didNodeMapper.selectOne(didNodeQueryWrapper);
        if (!(Objects.nonNull(didNode) && CollectionUtil.isNotEmpty(recoverAddressList)
                && StringUtils.equalsAnyIgnoreCase(didNode.getOwner(), Convert.toStrArray(recoverAddressList)))) {
            log.error("did不属于操作地址,req:{},didNode:{}", req, didNode);
            throw new BizException("Did does not belong to the operation address");
        }

        String gasBalance = reqEthCall(req.getNode());

        LatestDividentResp resp = new LatestDividentResp();
        resp.setNode(didNode.getNode());
        resp.setOwner(didNode.getOwner());
        resp.setSignature("0x123456");
        resp.setDivident(gasBalance);
        resp.setGasBalance(gasBalance);

        final long time = System.currentTimeMillis() / (24 * 60 * 60);
        resp.setTime(String.valueOf(time));
        return resp;
    }

    private String reqEthCall(String node) {
        String reqUrl = chainConfig.getBlockReqHost();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("jsonrpc", "2.0");
        paramMap.put("method", "eth_call");

        List<Object> paramList = Lists.newArrayList();
        Map<String, Object> secondParamMap = Maps.newHashMap();
        secondParamMap.put("to", contractAddress);
        secondParamMap.put("data", FunctionEncodeUtils.getGasBalanceEncodeNoPrefix(node));
        paramList.add(secondParamMap);
        paramList.add("latest");

        paramMap.put("params", paramList);
        paramMap.put("id", "1");

        return Optional.ofNullable(HttpUtils.postJson(reqUrl, JSONUtil.toJsonStr(paramMap)))
                .map(JSONUtil::parseObj)
                .map(jsonObj -> jsonObj.getStr("result")).orElse(null);
    }



}
