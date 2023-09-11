package com.backend.EMS.DTO;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Data Transfer Object (DTO) for handling login information.
 */
@Getter
@Setter
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

