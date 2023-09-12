package com.backend.EMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AnnotationValidation extends RuntimeException{
    /**
     * Constructs a new UserNotFound exception with the specified error message.
     *
     * @param message The error message.
     */

    public AnnotationValidation(final String message) {
        super(message);
    }

}
