package com.afkl.cases.df.exceptions;

public class ValidationException extends RuntimeException {

    @lombok.Getter
    private String domain = null;

    public ValidationException(final String message, final String domain, final Throwable e) {
        this(message, e);
        this.domain = domain;
    }

    public ValidationException(final String message, final Throwable e) {
        super(message, e);
    }
}
