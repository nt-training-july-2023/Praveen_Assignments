package com.backend.EMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
/**
 * Data Transfer Object (DTO) for handling IsRequested information.
 */
public class IsRequestedInDto {
    /**
     * The employeeId of the employee.
     */
    @NotNull
    private Long employeeId;
    /**
     * The id of the employee.
     */
    @NotNull
    private Long id;
    /**
     * @return the employeeId
     */
    public final Long getEmployeeId() {
        return employeeId;
    }
    /**
     * @param employeeIdValue the employeeId to set
     */
    public final void setEmployeeId(final Long employeeIdValue) {
        this.employeeId = employeeIdValue;
    }
    /**
     * @return the id
     */
    public final Long getId() {
        return id;
    }
    /**
     * @param managerId the managerEmailValue to set
     */
    public final void setId(final Long managerId) {
        this.id = managerId;
    }
    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, id);
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
        IsRequestedInDto other = (IsRequestedInDto) obj;
        return Objects.equals(employeeId, other.employeeId)
                && Objects.equals(id, other.id);
    }
    @Override
    public final String toString() {
        return "IsRequestedInDto [employeeId=" + employeeId
                + ", id=" + id + "]";
    }

}
