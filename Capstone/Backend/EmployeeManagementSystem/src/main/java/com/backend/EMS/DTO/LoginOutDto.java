package com.backend.EMS.DTO;

import java.util.Objects;

import com.backend.EMS.Model.Role;

/**
 * Data Transfer Object (DTO) for handling login response information.
 */
public class LoginOutDto {
    /**
     * The role associated with the login response.
     */
    private Role role;

    /**
     * The message associated with the login response.
     */
    private String message;

    /**
     * The name associated with the login response.
     */
    private String name;

    /**
     * The id associated with the login response.
     */
    private Long id;

    /**
     * Get the id associated with the login response.
     *
     * @return The id associated with the login response.
     */
    public final Long getId() {
        return id;
    }

    /**
     * Set the id associated with the login response.
     *
     * @param ids The id to set for the login response.
     */
    public final void setId(final Long ids) {
        this.id = ids;
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
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameValue the name to set
     */
    public final void setName(final String nameValue) {
        this.name = nameValue;
    }

    /**
     * Default constructor for LoginOutDto.
     */
    public LoginOutDto() {
        super();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, message, name, role);
    }

    /**
     * Constructs a new LoginOutDto object with the specified parameters.
     *
     * @param roles The role associated with the login response.
     * @param messages The message associated with the login response.
     * @param names The name associated with the login response.
     * @param ids The id associated with the login response.
     */
    public LoginOutDto(final Role roles, final String messages,
            final String names, final Long ids) {
        super();
        this.role = roles;
        this.message = messages;
        this.name = names;
        this.id = ids;
    }

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
        LoginOutDto other = (LoginOutDto) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(message, other.message)
                && Objects.equals(name, other.name) && role == other.role;
    }

    @Override
    public final String toString() {
        return "LoginOutDto [role=" + role + ", message="
    + message + ", name=" + name + ", email=" + id + "]";
    }
}
