package com.galaxy.diddao.service;

import com.galaxy.diddao.resp.DidNodeResp;

import java.util.List;

/**
 * @Author Ant
 * @Date 2022/9/22 15:11
 * @Description:
 */
public interface DidNodeService {
    List<DidNodeResp> getDidNodeListByAddress(String owner);
}
