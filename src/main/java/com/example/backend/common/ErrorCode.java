package com.example.backend.common;

public enum ErrorCode {

    APPLICATION_SETTING_NOT_FOUND("APPLICATION_SETTING_NOT_FOUND"),
    FILTER_NOT_FOUND("FILTER_NOT_FOUND"),
    USER_NOT_FOUND("USER_NOT_FOUND");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
