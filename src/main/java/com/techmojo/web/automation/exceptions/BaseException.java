package com.techmojo.web.automation.exceptions;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -5361832289177657535L;

    private String errorCode;
    private String errorMessage;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    public BaseException(String message) {
        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
