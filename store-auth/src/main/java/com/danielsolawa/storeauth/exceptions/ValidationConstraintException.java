package com.danielsolawa.storeauth.exceptions;

public class ValidationConstraintException extends RuntimeException {

    public ValidationConstraintException() {
        super();
    }

    public ValidationConstraintException(String s) {
        super(s);
    }

    public ValidationConstraintException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidationConstraintException(Throwable throwable) {
        super(throwable);
    }

    protected ValidationConstraintException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
