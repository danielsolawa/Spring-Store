package com.danielsolawa.storeauth.exceptions;

public class ActivateTokenExpiredException extends RuntimeException {

    public ActivateTokenExpiredException() {
        super();
    }

    public ActivateTokenExpiredException(String s) {
        super(s);
    }

    public ActivateTokenExpiredException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ActivateTokenExpiredException(Throwable throwable) {
        super(throwable);
    }

    protected ActivateTokenExpiredException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
