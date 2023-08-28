package com.backend.EMS.DTO;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
public class Response_DTO {
    
    private int statusCode;
    private String message;
    
    /**
     * Constructor to create a response with status code and message.
     *
     * @param statusCode The status code.
     * @param message    The message.
     */
    public Response_DTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    
    /**
     * Default constructor.
     */
    public Response_DTO() {
        // Default constructor
    }
    
    /**
     * Get the status code.
     *
     * @return The status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Set the status code.
     *
     * @param statusCode The status code to set.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    /**
     * Get the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Set the message.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "Response_DTO [statusCode=" + statusCode + ", message=" + message + "]";
    }
}

