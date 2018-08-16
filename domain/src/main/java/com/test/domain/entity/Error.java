package com.test.domain.entity;

public class Error extends Exception {

    private ErrorType errorType;

    public Error(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
