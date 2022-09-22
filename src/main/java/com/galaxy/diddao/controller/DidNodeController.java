package com.galaxy.diddao.controller;

import com.galaxy.diddao.resp.DidNodeResp;
import com.galaxy.diddao.service.DidNodeService;
import com.galaxy.diddao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author leyangjie
 * @Date 2022/9/22 14:57
 * @Description:
 */
@RequestMapping("/didNode")
@RestController
public class DidNodeController {

    @Autowired
    private DidNodeService didNodeService;

    @GetMapping("/getDidNodeListByAddress")
    public R<List<DidNodeResp>> getDidNodeListByAddress(@RequestParam String owner) {
        return R.ok(didNodeService.getDidNodeListByAddress(owner));
    }

}
