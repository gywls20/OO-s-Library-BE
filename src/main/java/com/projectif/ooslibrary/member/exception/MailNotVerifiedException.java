package com.projectif.ooslibrary.member.exception;

public class MailNotVerifiedException extends RuntimeException {

    public MailNotVerifiedException() {
        super();
    }

    public MailNotVerifiedException(String message) {
        super(message);
    }

    public MailNotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
