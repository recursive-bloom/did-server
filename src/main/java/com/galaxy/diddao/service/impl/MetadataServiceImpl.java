package com.galaxy.diddao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galaxy.diddao.entity.DidNode;
import com.galaxy.diddao.entity.DidNodeKvDb;
import com.galaxy.diddao.enums.NameHashEnum;
import com.galaxy.diddao.exception.BizException;
import com.galaxy.diddao.mapper.DidNodeKvDbMapper;
import com.galaxy.diddao.mapper.DidNodeMapper;
import com.galaxy.diddao.resp.MetadataResp;
import com.galaxy.diddao.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.utils.Numeric;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
@Slf4j
@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private DidNodeKvDbMapper didNodeKvDbMapper;

    @Autowired
    private DidNodeMapper didNodeMapper;

    @Override
    public MetadataResp getNodeOneInfo(String node, String itemKey) {
        final LambdaQueryWrapper<DidNodeKvDb> didNodeKvDbQuery = Wrappers.<DidNodeKvDb>lambdaQuery()
                .eq(DidNodeKvDb::getNode, node)
                .eq(DidNodeKvDb::getItemKey, itemKey)
                .orderByDesc(DidNodeKvDb::getId).last(" limit 1");

        final DidNodeKvDb didNodeKvDb = didNodeKvDbMapper.selectOne(didNodeKvDbQuery);
        Assert.notNull(didNodeKvDb,"Not found according to itemkey and node");

        final DidNodeKvDb maxdidNodeKvDb = didNodeKvDbMapper.selectById(didNodeKvDb.getId());
        String metadataName = getMatedataName(maxdidNodeKvDb);

        MetadataResp resp = new MetadataResp();
        resp.setName(metadataName);
        resp.setImage(Numeric.toHexStringNoPrefix(didNodeKvDb.getValue()));
        return resp;
    }

    private String getMatedataName(DidNodeKvDb didNodeKvDb) {
        if(Objects.isNull(didNodeKvDb)){
            return null;
        }

        final LambdaQueryWrapper<DidNode> didNodeQuery = Wrappers.<DidNode>lambdaQuery()
                .eq(DidNode::getNode, didNodeKvDb.getNode())
                .eq(DidNode::getOwner, didNodeKvDb.getOwner())
                .orderByDesc(DidNode::getId)
                .last(" limit 1");

        final DidNode didNode = didNodeMapper.selectOne(didNodeQuery);
        if(Objects.isNull(didNode)){
            log.error("根据node:{} ,owner:{}未找到对应数据",didNodeKvDb.getNode(),didNodeKvDb.getOwner());
            throw new BizException("No data found according to owner and node");
        }

        StringJoiner sj = new StringJoiner(".");
        sj.add(didNode.getName());
        sj.add(NameHashEnum.getNameByHash(didNode.getParentNode()));
        return sj.toString();
    }
}
