package com.afkl.cases.df.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<AppError> handle4xx(final ValidationException ex,
                                                      final HttpServletRequest request) {
        final String bodyOfResponse = ex.getMessage();

        final AppError appError = new AppError("v1",  "400", bodyOfResponse, ex.getDomain(),
        "Validation exception", bodyOfResponse, "errorReportUri");

        return new ResponseEntity<>(appError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<AppError> handle5xx(final ServerException ex,
                                                 final HttpServletRequest request) {
        final String bodyOfResponse = ex.getMessage();

        final AppError appError = new AppError("v1",  "500", bodyOfResponse, ex.getDomain(),
                "Server exception", bodyOfResponse, "errorReportUri");

        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
