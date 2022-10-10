package com.galaxy.diddao.exception;


import lombok.Data;

import java.io.Serializable;

@Data
public class BizException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -4462697324066226664L;

    private static final Integer ERROR_CODE = 500;

    private String message;

    private Integer code;


    public BizException(String errorMsg) {
        this(errorMsg, ERROR_CODE);
    }


    public BizException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
