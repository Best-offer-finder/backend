package com.example.backend.exception.exceptions;

import com.example.backend.common.ErrorCode;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

}
