package com.galaxy.diddao.service;

import com.galaxy.diddao.resp.MetadataResp;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
public interface MetadataService {
    MetadataResp getNodeOneInfo(String node, String itemKey);
}
