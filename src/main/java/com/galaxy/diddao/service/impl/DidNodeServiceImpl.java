package com.galaxy.diddao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.entity.DidNode;
import com.galaxy.diddao.entity.ReverseRecord;
import com.galaxy.diddao.mapper.DidNodeMapper;
import com.galaxy.diddao.mapper.ReverseRecordMapper;
import com.galaxy.diddao.resp.DidNodeResp;
import com.galaxy.diddao.service.DidNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author leyangjie
 * @Date 2022/9/22 15:11
 * @Description:
 */
@Service
public class DidNodeServiceImpl implements DidNodeService {

    @Autowired
    private DidNodeMapper didNodeMapper;

    @Autowired
    private ReverseRecordMapper reverseRecordMapper;

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
}
