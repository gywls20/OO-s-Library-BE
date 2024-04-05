package com.projectif.ooslibrary.member.exception;

public class OAuth2LoginNoSessionValueException extends RuntimeException {

    public OAuth2LoginNoSessionValueException() {
        super();
    }

    public OAuth2LoginNoSessionValueException(String message) {
        super(message);
    }

    public OAuth2LoginNoSessionValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
