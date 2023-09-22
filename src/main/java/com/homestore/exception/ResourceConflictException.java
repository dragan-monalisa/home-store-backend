package com.homestore.exception;

import lombok.Getter;

@Getter
public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(String message) {
        super(message);
    }
}