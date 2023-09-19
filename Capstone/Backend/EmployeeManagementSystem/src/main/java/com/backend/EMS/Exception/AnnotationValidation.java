package com.backend.EMS.Exception;

/**
 * Custom exception class to represent validations for dto.
 */
public class AnnotationValidation extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFound exception with the specified error message.
     *
     * @param message The error message.
     */

    public AnnotationValidation(final String message) {
        super(message);
    }

}
