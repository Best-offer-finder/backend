package com.example.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private long status;

    private String message;

    public ErrorDto(long status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorDto(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
