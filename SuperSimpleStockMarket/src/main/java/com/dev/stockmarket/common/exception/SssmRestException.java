package com.dev.stockmarket.common.exception;

import com.dev.stockmarket.common.model.Code;

public class SssmRestException extends RuntimeException {

    private static final long serialVersionUID = 111988443560228411L;

    protected Code errorCode;

    public SssmRestException() {
        super();
    }

    public SssmRestException(String message) {
        super(message);
    }

    public SssmRestException(Code errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SssmRestException(Code errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Code getErrorCode() {
        return errorCode;
    }
}
