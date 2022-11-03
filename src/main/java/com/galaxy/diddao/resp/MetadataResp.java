package com.galaxy.diddao.resp;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
@Data
public class MetadataResp implements Serializable {
    private static final long serialVersionUID = 1723329704350950058L;

    private String name;

    private String symbol;

    private String description;

    private String image;

    private String externalUrl = "https://diddao.io";

    private List<Object> properties ;

    private List<Object> attributes;

    private List<Object> collection;
}
