package com.projectif.ooslibrary.member.exception;

public class NoSuchVerifyCodeException extends RuntimeException {

    public NoSuchVerifyCodeException() {
        super();
    }

    public NoSuchVerifyCodeException(String message) {
        super(message);
    }

    public NoSuchVerifyCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
