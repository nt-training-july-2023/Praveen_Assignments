package com.backend.EMS.DTO;

import java.beans.JavaBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling login information.
 */
@Data
@JavaBean
@NoArgsConstructor
@AllArgsConstructor
public class Login_DTO {

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
    public String getEmail() {
        return email;
    }

    /**
     * Set the email.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login_DTO [email=" + email + ", password=" + password + "]";
    }
}

