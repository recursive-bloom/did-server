package com.galaxy.diddao.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Ant
 * @Date 2022/9/21 7:18
 * @Description:
 */
@Data
public class Logs {
    private String address;
    private List<String> topics;
    private String data;
    private String blockNumber;
    private String transactionHash;
    private String transactionIndex;
    private String blockHash;
    private String logIndex;
    private boolean removed;
}
