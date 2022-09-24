package com.galaxy.diddao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(type = IdType.AUTO)
    private Long id;

    private String owner;

    private String node;
}
