package com.projectif.ooslibrary.member.exception;

public class NoSuchMemberException extends RuntimeException {

    public NoSuchMemberException() {
        super();
    }

    public NoSuchMemberException(String message) {
        super(message);
    }

    public NoSuchMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
