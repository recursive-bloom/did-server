package com.hw.did.service;

import com.hw.did.dto.BlockTxDetailInfo;

/**
 * @Author leyangjie
 * @Date 2022/9/20 18:10
 * @Description:
 */
public interface FunctionEventParseService {

    /**
     * 事件数据解析
     */
    void eventParse(BlockTxDetailInfo blockTxDetailInfo);

    /**
     * 数据插入
     */
    void insertData();
}
