package com.FlexPay.FlexPay.exception;

public class InvalidLoginCredentialException extends RuntimeException {
    public InvalidLoginCredentialException(String message) {
        super(message);
    }
}
