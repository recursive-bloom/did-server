package com.galaxy.diddao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
@TableName(value = "did_node_kvdb")
@Data
public class DidNodeKvDb   implements Serializable {

    private static final long serialVersionUID = 873163308843709549L;

    @TableId(type = IdType.AUTO)
    private Long id;


    private String node;

    private String owner;

    private String itemKey;

    private byte[] value;
}
