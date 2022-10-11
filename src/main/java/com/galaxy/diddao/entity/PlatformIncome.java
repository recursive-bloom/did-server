package com.galaxy.diddao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/10/11
 * @Description:
 */
@Data
public class PlatformIncome extends TxBase implements Serializable {
    private static final long serialVersionUID = -7562396022882537874L;


    private Long id;

    /**
     * 记录那一天(从1970年1月1号作为第一天开始)
     */
    private Long date;

    /**
     * 今日收益
     */
    private String income;

    /**
     * 地址
     */
    private String item;

    /**
     * 收益累计
     */
    private String accumulated;
}
