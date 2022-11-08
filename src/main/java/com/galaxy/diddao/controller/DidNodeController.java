package com.galaxy.diddao.controller;

import cn.hutool.core.lang.Assert;
import com.galaxy.diddao.req.LatestDividentReq;
import com.galaxy.diddao.resp.DidNodeResp;
import com.galaxy.diddao.resp.LatestDividentResp;
import com.galaxy.diddao.service.DidNodeService;
import com.galaxy.diddao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ant
 * @Date 2022/9/22 14:57
 * @Description:
 */
@RequestMapping("/didNode")
@RestController
public class DidNodeController {

    @Autowired
    private DidNodeService didNodeService;

    @GetMapping("/getDidNodeListByAddress/{owner}")
    public R<List<DidNodeResp>> getDidNodeListByAddress(@PathVariable String owner) {
        return R.ok(didNodeService.getDidNodeListByAddress(owner));
    }

    @PostMapping("/getLatestDivident")
    public R<LatestDividentResp> getLatestDivident(@RequestBody LatestDividentReq req) {
        Assert.notBlank(req.getNode(), "node parameter is null");
        Assert.notBlank(req.getSignature(), "signature parameter is null");
        return R.ok(didNodeService.getLatestDivident(req));
    }

}
