package com.example.backend.exception.exceptions;

import com.example.backend.common.ErrorCode;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

}
