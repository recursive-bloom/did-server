package com.galaxy.diddao.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ant
 * @Date 2022/9/23
 * @Description:
 */
@Data
public class LatestDividentResp implements Serializable {
    private static final long serialVersionUID = 537832431558974686L;

    private String owner;

    private String node;

    private String time;

    private String signature;

    private String gasBalance;

    private String divident;
}
