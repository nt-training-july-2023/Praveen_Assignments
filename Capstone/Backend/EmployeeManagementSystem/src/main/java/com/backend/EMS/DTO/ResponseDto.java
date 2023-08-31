package com.backend.EMS.DTO;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
@Data
public class ResponseDto {
    /**
     * The Http statusCode associated with the response.
     */
    private int statusCode;
    /**
     * The Response message associated with the response.
     */
    private String message;
    /**
     * The Response data is associated with the response.
     */
    private Object data;
}
