package com.backend.EMS.DTO;

import java.util.Objects;
/**
 * Data Transfer Object (DTO) for handling IsRequested information.
 */
public class IsRequestedOutDto {
    /**
     * The isRequested of the employee.
     */
    private boolean isRequested;

    /**
     * @return the isRequested
     */
    public final boolean isRequested() {
        return isRequested;
    }

    /**
     * @param isRequestedValue the isRequested to set
     */
    public final void setRequested(final boolean isRequestedValue) {
        this.isRequested = isRequestedValue;
    }

    @Override
    public final String toString() {
        return "IsRequestedOutDto [isRequested=" + isRequested + "]";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(isRequested);
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
        IsRequestedOutDto other = (IsRequestedOutDto) obj;
        return isRequested == other.isRequested;
    }

}
