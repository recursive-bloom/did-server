package com.galaxy.diddao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/22 12:35
 * @Description:
 */
@Data
public class DidNode extends TxBase implements Serializable {

    private static final long serialVersionUID = 781238059146136052L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String node;

    private String parentNode;

    private String owner;

    private Long expire;

    private Long ttl;

    private Long transfer;

    private String name;


}
