package com.afkl.cases.df.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@lombok.Data
public class AppError {

    private final String apiVersion;
    private final ErrorBlock error;

    public AppError(final String apiVersion, final String code, final String message, final String domain,
                    final String reason, final String errorMessage, final String errorReportUri) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message, domain, reason, errorMessage, errorReportUri);
    }

    public static AppError fromDefaultAttributeMap(final String apiVersion,
                                                   final Map<String, Object> defaultErrorAttributes,
                                                   final String sendReportBaseUri) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new AppError(
                apiVersion,
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("path", "no domain available"),
                (String) defaultErrorAttributes.getOrDefault("error", "no reason available"),
                (String) defaultErrorAttributes.get("message"),
                sendReportBaseUri
        );
    }

    public Map<String, Object> toAttributeMap() {
        return Map.of(
                "apiVersion", apiVersion,
                "error", error
        );
    }

    @lombok.AllArgsConstructor
    @lombok.Data
    private static final class ErrorBlock {

        @JsonIgnore
        private final UUID uniqueId;
        private final String code;
        private final String message;
        private final List<Error> errors;

        ErrorBlock(final String code, final String message, final String domain,
                   final String reason, final String errorMessage, final String errorReportUri) {
            this.code = code;
            this.message = message;
            this.uniqueId = UUID.randomUUID();
            this.errors = Collections.singletonList(
                    new Error(domain, reason, errorMessage, errorReportUri + "?id=" + uniqueId)
            );
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s, final String message) {
            return new ErrorBlock(s.uniqueId, s.code, message, s.errors);
        }

    }

    @lombok.AllArgsConstructor
    @lombok.Data
    private static final class Error {
        private final String domain;
        private final String reason;
        private final String message;
        private final String sendReport;
    }

}
