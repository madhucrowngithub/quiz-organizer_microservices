package com.quiz.question_services.commons.error.custom;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntityException(final String message) {
        super(message);
    }

    public DuplicateEntityException(final Throwable cause) {
        super(cause);
    }

}
