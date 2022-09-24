package com.galaxy.diddao.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/23
 * @Description:
 */
@Data
public class LatestDividentReq implements Serializable {
    private static final long serialVersionUID = 324102272087865328L;

    /**
     * 签名
     */
    private String signature;

    private String node;
}
