package com.dev.stockmarket.common.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat (shape = JsonFormat.Shape.OBJECT)
public enum ResultCode implements Code {

    SUCCESS                                         (200, "SUCCESS"),
    STOCK_NOT_FOUND                                 (404, "STOCK_NOT_FOUND"),
    INTERNAL_SERVER_ERROR                           (500, "INTERNAL_SERVER_ERROR"),
    BAD_REQUEST                                     (400, "BAD_REQUEST");

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}