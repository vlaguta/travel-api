package com.travel.exception;

public class BusinessException extends AbstractException {

    private Integer httpCode;

    public BusinessException(String message, Integer httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}
