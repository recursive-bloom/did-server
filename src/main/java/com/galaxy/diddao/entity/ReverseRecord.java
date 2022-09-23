package com.galaxy.diddao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/22 13:54
 * @Description:
 */
@Data
public class ReverseRecord implements Serializable {

    private static final long serialVersionUID = -5902161760738370835L;

    private Long id;

    private String owner;

    private String node;
}
