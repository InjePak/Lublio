package com.inje.lublio.user.exception;

public class UnmatchedPasswordException extends RuntimeException {
    public UnmatchedPasswordException(String msg) {
        super(msg);
    }
}
