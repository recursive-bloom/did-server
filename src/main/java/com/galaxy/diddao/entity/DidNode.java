package com.galaxy.diddao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leyangjie
 * @Date 2022/9/22 12:35
 * @Description:
 */
@Data
public class DidNode implements Serializable {

    private static final long serialVersionUID = 781238059146136052L;

    private Long id;

    private String node;

    private String parentNode;

    private String owner;

    private Long expire;

    private Long ttl;

    private Long transfer;

    private String name;
}
