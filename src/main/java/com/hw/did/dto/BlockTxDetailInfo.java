package com.hw.did.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author leyangjie
 * @Date 2022/9/18 16:23
 * @Description:
 */
@Data
public class BlockTxDetailInfo implements Serializable {
    private static final long serialVersionUID = -2223462067408365341L;

    private String blockHash;
    private String blockNumber;
    private String contractAddress;
    private String cumulativeGasUsed;
    private String effectiveGasPrice;
    private String from;
    private String gasUsed;
    private List<Logs> logs;
    private String logsBloom;
    private String status;
    private String to;
    private String transactionHash;
    private String transactionIndex;
    private String type;
}

@Data
class Logs{
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
