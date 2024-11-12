package com.example.caching.caching.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
    public ResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
