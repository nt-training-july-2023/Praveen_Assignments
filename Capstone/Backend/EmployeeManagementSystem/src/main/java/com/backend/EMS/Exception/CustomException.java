package com.backend.EMS.Exception;

/**
 * Custom exception class to represent validations for dto.
 */
public class CustomException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFound exception with the specified error message.
     *
     * @param message The error message.
     */
    public CustomException(final String message) {
        super(message);
    }

}
