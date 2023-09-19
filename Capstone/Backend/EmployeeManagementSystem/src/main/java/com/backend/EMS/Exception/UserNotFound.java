package com.backend.EMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to represent a user not found scenario.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFound exception with the specified error message.
     *
     * @param message The error message.
     */
    public UserNotFound(final String message) {
        super(message);
    }
}
