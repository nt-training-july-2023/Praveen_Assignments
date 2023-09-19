package com.backend.EMS.DTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling employee names.
 */
public class EmployeeNameDto {
    /**
     * The name of the employee.
     */
    private String name;

    /**
     * Default constructor for EmployeeNameDto.
     */
    public EmployeeNameDto() {
        super();
    }

    /**
     * Constructor for initializing EmployeeNameDto with a name.
     *
     * @param employeeName The name of the employee.
     */
    public EmployeeNameDto(final String employeeName) {
        super();
        this.name = employeeName;
    }

    /**
     * Get the name of the employee.
     *
     * @return The name of the employee.
     */
    public final String getName() {
        return name;
    }

    /**
     * Set the name of the employee.
     *
     * @param employeeName The name of the employee.
     */
    public final void setName(final String employeeName) {
        this.name = employeeName;
    }

    /**
     * Generate a hash code for this object based on the name.
     *
     * @return The generated hash code.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compare this object with another object for equality based on the name.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmployeeNameDto other = (EmployeeNameDto) obj;
        return Objects.equals(name, other.name);
    }

    /**
     * Get a string representation of this object.
     *
     * @return A string representation of the object, including the name.
     */
    @Override
    public final String toString() {
        return "EmployeeNameDto [name=" + name + "]";
    }
}
