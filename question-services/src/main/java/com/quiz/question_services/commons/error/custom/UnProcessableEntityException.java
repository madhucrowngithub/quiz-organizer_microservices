package com.quiz.question_services.commons.error.custom;

public class UnProcessableEntityException extends RuntimeException{
    public UnProcessableEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnProcessableEntityException(final String message) {
        super(message);
    }

    public UnProcessableEntityException(final Throwable cause) {
        super(cause);
    }
}
