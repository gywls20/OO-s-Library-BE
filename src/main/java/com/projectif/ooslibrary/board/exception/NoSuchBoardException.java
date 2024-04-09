package com.projectif.ooslibrary.board.exception;

public class NoSuchBoardException extends RuntimeException {

    public NoSuchBoardException() {
        super();
    }

    public NoSuchBoardException(String message) {
        super(message);
    }

    public NoSuchBoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
