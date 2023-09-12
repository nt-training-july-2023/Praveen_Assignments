package com.backend.EMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    /**
     * The Http statusCode associated with the response.
     */
//    private int statusCode;
//    /**
//     * The Response message associated with the response.
//     */
    private String message;
    
 
//    /**
//     * The Response data is associated with the response.
//     */
//    private Object data;
}

