package com.galaxy.diddao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/10/11
 * @Description:
 */
@Data
public class UserDivident extends TxBase implements Serializable {
    private static final long serialVersionUID = -5931814712385381110L;


    private Long id;

    /**
     * 记录那一天(从1970年1月1号作为第一天开始)
     */
    private Long date;

    /**
     * 金额
     */
    private String amount;

    /**
     * 地址
     */
    private String owner;

    /**
     * node
     */
    private String node;

    /**
     * 累计分红
     */
    private String accumulated;
}
