package com.projectif.ooslibrary.member.exception;

public class MailNotSendException extends RuntimeException {

    public MailNotSendException() {
        super();
    }

    public MailNotSendException(String message) {
        super(message);
    }

    public MailNotSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
