package com.danielsolawa.storeauth.exceptions;

public class TokenMismatchException extends RuntimeException {

    public TokenMismatchException() {
        super();
    }

    public TokenMismatchException(String s) {
        super(s);
    }

    public TokenMismatchException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TokenMismatchException(Throwable throwable) {
        super(throwable);
    }

    protected TokenMismatchException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
