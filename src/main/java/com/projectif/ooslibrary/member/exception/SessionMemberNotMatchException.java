package com.projectif.ooslibrary.member.exception;

public class SessionMemberNotMatchException extends RuntimeException {

    public SessionMemberNotMatchException() {
        super();
    }

    public SessionMemberNotMatchException(String message) {
        super(message);
    }

    public SessionMemberNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
