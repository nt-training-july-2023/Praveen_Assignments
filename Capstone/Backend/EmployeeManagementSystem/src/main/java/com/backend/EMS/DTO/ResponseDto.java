package com.backend.EMS.DTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
public class ResponseDto {
    /**
     * The Response message associated with the response.
     */
    private String message;

    /**
     * Get the message associated with the response.
     *
     * @return The message associated with the response.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Set the message associated with the response.
     *
     * @param messages The message associated with the response.
     */
    public final void setMessage(final String messages) {
        this.message = messages;
    }

    /**
     * Default constructor for ResponseDto.
     */
    public ResponseDto() {
        super();
    }

    @Override
    public final String toString() {
        return "ResponseDto [message=" + message + "]";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(message);
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
        ResponseDto other = (ResponseDto) obj;
        return Objects.equals(message, other.message);
    }

    /**
     *
     * @param messages the messages
     */
    public ResponseDto(final String messages) {
        super();
        this.message = messages;
    }
}
