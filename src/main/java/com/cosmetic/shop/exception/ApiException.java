package com.cosmetic.shop.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
