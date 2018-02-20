package com.danielsolawa.storeauth.exceptions;

public class AccountAlreadyActivatedException extends RuntimeException {

    public AccountAlreadyActivatedException() {
        super();
    }

    public AccountAlreadyActivatedException(String s) {
        super(s);
    }

    public AccountAlreadyActivatedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AccountAlreadyActivatedException(Throwable throwable) {
        super(throwable);
    }

    protected AccountAlreadyActivatedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
