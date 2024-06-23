package com.inje.lublio.user.exception;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String msg) {
        super(msg);
    }
}
