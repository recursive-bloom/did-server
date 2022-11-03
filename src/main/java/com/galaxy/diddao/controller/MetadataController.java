package com.galaxy.diddao.controller;

import cn.hutool.core.lang.Assert;
import com.galaxy.diddao.resp.MetadataResp;
import com.galaxy.diddao.service.MetadataService;
import com.galaxy.diddao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
@RequestMapping("/metadata")
@RestController
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping("/nodeOne")
    public R<MetadataResp> getNodeOneInfo(String node, String itemKey){
        Assert.notBlank(node,"node is empty");
        Assert.notBlank(itemKey,"itemKey is empty");
        return R.ok(metadataService.getNodeOneInfo(node,itemKey));
    }
}
