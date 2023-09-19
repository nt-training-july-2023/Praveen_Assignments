package com.backend.EMS.DTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling login information.
 */
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
     * Get the email associated with the login.
     *
     * @return The email associated with the login.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Set the email associated with the login.
     *
     * @param emails The email to set for the login.
     */
    public final void setEmail(final String emails) {
        this.email = emails;
    }

    /**
     * Get the password associated with the login.
     *
     * @return The password associated with the login.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Set the password associated with the login.
     *
     * @param passwords The password to set for the login.
     */
    public final void setPassword(final String passwords) {
        this.password = passwords;
    }

    /**
     * Generate a string representation of this object.
     *
     * @return A string representation of the LoginDto object,
     *  including email and password.
     */
    @Override
    public final String toString() {
        return "LoginDto [email=" + email + ", password=" + password + "]";
    }

    /**
     * Default constructor for LoginDto.
     */
    public LoginDto() {
        super();
    }

    /**
     * Generate a hash code for this object based on email and password.
     *
     * @return The generated hash code.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(email, password);
    }

    /**
     * Compare this object with another object for
     *  equality based on email and password.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LoginDto other = (LoginDto) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(password, other.password);
    }
}


