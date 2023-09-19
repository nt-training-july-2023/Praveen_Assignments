package com.backend.EMS.DTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) for handling employee information.
 */
public class EmployeeDto {
    /**
     * The id of the employee.
     */
    private Long id;

    /**
     * The name of the employee.
     */
    @NotBlank(message = "Name cannot be empty")
    private String name;

    /**
     * The email of the employee.
     */
    @Pattern(regexp = ".+@nucleusteq\\.com$", message = "Email"
            + " must end with @nucleusteq.com")
    private String email;

    /**
     * The employee ID of the employee.
     */
    @Pattern(regexp = "^N\\d{4}$", message = "Employee ID should be"
            + " in the pattern NXXXX (X should be numbers)")
    private String empId;

    /**
     * The date of birth of the employee.
     */
    @NotBlank(message = "Date of birth cannot be empty")
    private String dob;
    /**
     * The date of joining of the employee.
     */
    @NotBlank(message = "Date of joining cannot be empty")
    private String doj;

    /**
     * The location of the employee.
     */
    private Location location;

    /**
     * The designation of the employee.
     */
    private Designation designation;
    /**
     * The contact number of the employee.
     */
    private Long contactNo;
    /**
     * The password of the employee.
     */
    @NotBlank(message = "Password should not be empty")
    private String password;

    /**
     * The role of employee.
     */
    private Role role;

    /**
     * The skills of employee.
     */
    private List<String> skills;

    /**
     * Default constructor for EmployeeDto.
     */
    public EmployeeDto() {
        super();
    }

    @Override
    public final String toString() {
        return "EmployeeDto [id=" + id + ", name=" + name + ", email="
                             + email + ", empId=" + empId + ", dob=" + dob
                + ", doj=" + doj + ", location=" + location
                + ", designation=" + designation + ", contactNo="
                + contactNo + ", password=" + password + ", role="
                + role + ", skills=" + skills + "]";
    }

    /**
     * @return the id
     */
    public final Long getId() {
        return id;
    }

    /**
     * @param newId the id to set
     */
    public final void setId(final Long newId) {
        this.id = newId;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param newName the name to set
     */
    public final void setName(final String newName) {
        this.name = newName;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param newEmail the email to set
     */
    public final void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * @return the empId
     */
    public final String getEmpId() {
        return empId;
    }

    /**
     * @param newEmpId the empId to set
     */
    public final void setEmpId(final String newEmpId) {
        this.empId = newEmpId;
    }

    /**
     * @return the dob
     */
    public final String getDob() {
        return dob;
    }

    /**
     * @param newDob the dob to set
     */
    public final void setDob(final String newDob) {
        this.dob = newDob;
    }

    /**
     * @return the doj
     */
    public final String getDoj() {
        return doj;
    }

    /**
     * @param newDoj the doj to set
     */
    public final void setDoj(final String newDoj) {
        this.doj = newDoj;
    }

    /**
     * @return the location
     */
    public final Location getLocation() {
        return location;
    }

    /**
     * @param newLocation the location to set
     */
    public final void setLocation(final Location newLocation) {
        this.location = newLocation;
    }

    /**
     * @return the designation
     */
    public final Designation getDesignation() {
        return designation;
    }

    /**
     * @param newDesignation the designation to set
     */
    public final void setDesignation(final Designation newDesignation) {
        this.designation = newDesignation;
    }

    /**
     * @return the contactNo
     */
    public final Long getContactNo() {
        return contactNo;
    }

    /**
     * @param newContactNo the contactNo to set
     */
    public final void setContactNo(final Long newContactNo) {
        this.contactNo = newContactNo;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @param newPassword the password to set
     */
    public final void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * @return the role
     */
    public final Role getRole() {
        return role;
    }

    /**
     * @param newRole the role to set
     */
    public final void setRole(final Role newRole) {
        this.role = newRole;
    }

    /**
     * @return the skills
     */
    public final List<String> getSkills() {
        if (skills != null){
        return new ArrayList<>(skills);
        }
        else {
            return null;
        }
    }

    /**
     * @param newSkills the skills to set
     */
    public final void setSkills(final List<String> newSkills) {
        if (newSkills.size() != 0) {
            this.skills = new ArrayList<>(newSkills);
        } else {
            this.skills = null;
        }
    }

    @Override
    public final int hashCode() {
        return Objects.hash(contactNo, designation, dob, doj, email,
                empId, id, location, name, password, role, skills);
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
        EmployeeDto other = (EmployeeDto) obj;
        return Objects.equals(contactNo, other.contactNo)
                && designation == other.designation
                && Objects.equals(dob, other.dob)
                && Objects.equals(doj, other.doj)
                && Objects.equals(email, other.email)
                && Objects.equals(empId, other.empId)
                && Objects.equals(id, other.id) && location == other.location
                && Objects.equals(name, other.name)
                && Objects.equals(password, other.password)
                && role == other.role
                && Objects.equals(skills, other.skills);
    }
}


