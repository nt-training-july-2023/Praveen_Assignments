package com.backend.EMS.DTO;

import java.util.Objects;

import com.backend.EMS.Model.Role;

/**
 * Data Transfer Object (DTO) for handling login response information.
 */
public class LoginResponseDto {
    /**
     * The role associated with the login response.
     */
    private Role role;

    /**
     * The message associated with the login response.
     */
    private String message;

    /**
     * The email associated with the login response.
     */
    private String email;

    /**
     * Get the email associated with the login response.
     *
     * @return The email associated with the login response.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Set the email associated with the login response.
     *
     * @param emails The email to set for the login response.
     */
    public final void setEmail(final String emails) {
        this.email = emails;
    }

    /**
     * Get the role associated with the login response.
     *
     * @return The role associated with the login response.
     */
    public final Role getRole() {
        return role;
    }

    /**
     * Set the role associated with the login response.
     *
     * @param roles The role to set for the login response.
     */
    public final void setRole(final Role roles) {
        this.role = roles;
    }

    /**
     * Get the message associated with the login response.
     *
     * @return The message associated with the login response.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Set the message associated with the login response.
     *
     * @param messages The message to set for the login response.
     */
    public final void setMessage(final String messages) {
        this.message = messages;
    }

    /**
     * Default constructor for LoginResponseDto.
     */
    public LoginResponseDto() {
        super();
    }

    /**
     * Constructor for initializing LoginResponseDto. with role, message, and email.
     *
     * @param roles    The role associated with the login response.
     * @param messages The message associated with the login response.
     * @param emails   The email associated with the login response.
     */
    public LoginResponseDto(final Role roles, final String messages, final String emails) {
        super();
        this.role = roles;
        this.message = messages;
        this.email = emails;
    }

    /**
     * Generate a hash code for this object based on email, message, and role.
     *
     * @return The generated hash code.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(email, message, role);
    }

    /**
     * Compare this object with another object for equality based on email, message,
     * and role.
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
        LoginResponseDto other = (LoginResponseDto) obj;
        return Objects.equals(email, other.email) && Objects.equals(message, other.message) && role == other.role;
    }

    /**
     * Generate a string representation of this object.
     *
     * @return A string representation of the LoginResponseDto object, including
     *         role, message, and email.
     */
    @Override
    public final String toString() {
        return "LoginResponseDto [role=" + role + ", message=" + message + ", email=" + email + "]";
    }
}
