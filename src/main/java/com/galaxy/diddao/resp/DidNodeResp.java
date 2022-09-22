package com.galaxy.diddao.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leyangjie
 * @Date 2022/9/22 15:10
 * @Description:
 */
@Data
public class DidNodeResp implements Serializable {
    private static final long serialVersionUID = 969342665045992748L;

    private Long id;

    private String node;

    private String parentNode;

    private String owner;

    private Long expire;

    private Long ttl;

    private Long transfer;

    private String name;

    private Boolean isDefaultDidNode = false;
}
