package com.yang.common.exception;

import com.yang.common.constant.ApiResponseCode;

/**
 * 处理异常
 * 基础异常所有的都继承自这里
 *
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 2612992235262400823L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Integer code = null;

    public BaseException(String message) {
        super(message);
        this.code = ApiResponseCode.SERVICE_ERROR.getCode();
    }

    public BaseException(String message, Throwable t) {
        super(message, t);
        this.code = ApiResponseCode.SERVICE_ERROR.getCode();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }
}