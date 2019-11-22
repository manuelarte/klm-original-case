package com.afkl.cases.df.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    @lombok.Getter
    private String domain = null;

    public EntityNotFoundException(final String entityName, final String id) {
        this(String.format("%s with code %s not found", entityName, id));
        domain = entityName;
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
