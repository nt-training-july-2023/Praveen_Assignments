package com.backend.EMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a user has already been found.
 */
@ResponseStatus(HttpStatus.FOUND)
public class UserAlreadyFound extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new userAlreadyFound exception
        with the specified error message.
     *
     * @param message The error message explaining the exception.
     */
    public UserAlreadyFound(final String message) {
        super(message);
    }
}

