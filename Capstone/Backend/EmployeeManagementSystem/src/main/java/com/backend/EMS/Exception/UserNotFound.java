package com.backend.EMS.Exception;

/**
 * Custom exception class to represent a user not found scenario.
 */
public class UserNotFound extends RuntimeException {

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
