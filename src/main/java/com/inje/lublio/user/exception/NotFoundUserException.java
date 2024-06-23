package com.inje.lublio.user.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String msg) {
        super(msg);
    }
}
