package com.homestore.exception;

import lombok.Getter;

@Getter
public class UnsuccessfulOperationException extends RuntimeException{
    public UnsuccessfulOperationException(String message) {
        super(message);
    }
}