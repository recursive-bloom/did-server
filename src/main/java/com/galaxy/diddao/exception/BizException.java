package com.galaxy.diddao.exception;


import java.io.Serializable;

public class BizException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -4462697324066226664L;

    private static final Integer ERROR_CODE = 500;

    private String errorMsg;

    private Integer code;


    public BizException(String errorMsg) {
        this(errorMsg, ERROR_CODE);
    }


    public BizException(String errorMsg, Integer code) {
        this.errorMsg = errorMsg;
        this.code = code;
    }


}
