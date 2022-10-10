package com.galaxy.diddao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/27
 * @Description:
 */
@Data
public class Divident implements Serializable {


    private static final long serialVersionUID = 7002340425355840544L;
    private Long id;

    private String node;

    private String owner;

    private String userSignature;

    private String time;

    private String gas;

    private String value;

    private String platformSignature;

    private String platformAddress;

}
