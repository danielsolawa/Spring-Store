package com.danielsolawa.storeauth.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String s) {
        super(s);
    }

    public ResourceAlreadyExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ResourceAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    protected ResourceAlreadyExistsException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
