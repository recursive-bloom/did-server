package com.galaxy.diddao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/17 15:33
 * @Description:
 */
@Data
public class SysConfig implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * 配置value
     */
    private String configValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 结束时间
     */
    private String updateTime;

}
