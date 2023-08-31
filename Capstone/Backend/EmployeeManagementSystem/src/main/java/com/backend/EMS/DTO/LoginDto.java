package com.backend.EMS.DTO;



import lombok.Data;


/**
 * Data Transfer Object (DTO) for handling login information.
 */
@Data
public class LoginDto {

    /**
     * The email associated with the login.
     */
    private String email;

    /**
     * The password associated with the login.
     */
    private String password;

    /**
     * Get the email.
     *
     * @return The email.
     */

}

