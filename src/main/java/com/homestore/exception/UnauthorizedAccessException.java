package com.homestore.exception;

import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}