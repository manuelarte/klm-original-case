package com.afkl.cases.df.exceptions;

public class ServerException extends RuntimeException {

    @lombok.Getter
    private String domain = null;

    public ServerException(final String message, final String domain, final Throwable e) {
        super(message, e);
        this.domain = domain;
    }

    public ServerException(final String message, final Throwable e) {
        super(message, e);
    }
}
