package com.backend.EMS.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing an employee.
 */
@Entity
@Table(name = "Employee")
public class Employee {
    /**
     * The unique identifier for the employee.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the employee.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email of the employee.
     */
    @Column(nullable = false)
    private String email;

    /**
     * The employee ID of the employee.
     */
    @Column(nullable = false)
    private String empId;

    /**
     * The date of birth of the employee.
     */
    @Column(nullable = false)
    private String dob;

    /**
     * The date of joining of the employee.
     */
    @Column(nullable = false)
    private String doj;

    /**
     * The location of the employee.
     */
    @Enumerated(EnumType.STRING)
    private Location location;

    /**
     * The designation of the employee.
     */
    @Enumerated(EnumType.STRING)
    private Designation designation;

    /**
     * The contact number of the employee.
     */
    @Column(nullable = false)
    private Long contactNo;

    /**
     * The password of the employee.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role for the employee.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The skills of the employee.
     */
    @Column
    private List<String> skills;
    /**
     * The projectId of the employee.
     */
    @Column
    private Long projectId;

    /**
     * Get the project ID associated with the employee.
     *
     * @return The project ID.
     */
    public final Long getProjectId() {
        return projectId;
    }

    /**
     * Set the project ID for the employee.
     *
     * @param projectIds The project ID to set.
     */
    public final void setProjectId(final Long projectIds) {
        this.projectId = projectIds;
    }

    /**
     * Get the manager ID of the employee.
     *
     * @return The manager ID.
     */
    public final Long getManagerId() {
        return managerId;
    }

    /**
     * Set the manager ID for the employee.
     *
     * @param managerIds The manager ID to set.
     */
    public final void setManagerId(final Long managerIds) {
        this.managerId = managerIds;
    }
    /**
     * The managerId of the employee.
     */
    @Column
    private Long managerId;
    /**
     * The managerName of the employee.
     */
    @Column
    private String managerName;

    /**
     * Get the manager's name of the employee.
     *
     * @return The manager's name.
     */
    public final String getManagerName() {
        return managerName;
    }

    /**
     * Set the manager's name for the employee.
     *
     * @param managerNames The manager's name to set.
     */
    public final void setManagerName(final String managerNames) {
        this.managerName = managerNames;
    }

    /**
     * Get the employee's unique identifier.
     *
     * @return The employee's unique identifier.
     */
    public final long getId() {
        return id;
    }

    /**
     * Set the employee's unique identifier.
     *
     * @param ids The unique identifier to set.
     */
    public final void setId(final long ids) {
        this.id = ids;
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
     * @param names The name to set.
     */
    public final void setName(final String names) {
        this.name = names;
    }

    /**
     * Get the email of the employee.
     *
     * @return The email of the employee.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Set the email of the employee.
     *
     * @param emails The email to set.
     */
    public final void setEmail(final String emails) {
        this.email = emails;
    }

    /**
     * Get the employee ID of the employee.
     *
     * @return The employee ID of the employee.
     */
    public final String getEmpId() {
        return empId;
    }

    /**
     * Set the employee ID of the employee.
     *
     * @param empIds The employee ID to set.
     */
    public final void setEmpId(final String empIds) {
        this.empId = empIds;
    }

    /**
     * Get the date of birth of the employee.
     *
     * @return The date of birth of the employee.
     */
    public final String getDob() {
        return dob;
    }

    /**
     * Set the date of birth of the employee.
     *
     * @param dobs The date of birth to set.
     */
    public final void setDob(final String dobs) {
        this.dob = dobs;
    }

    /**
     * Get the date of joining of the employee.
     *
     * @return The date of joining of the employee.
     */
    public final String getDoj() {
        return doj;
    }

    /**
     * Set the date of joining of the employee.
     *
     * @param dojs The date of joining to set.
     */
    public final void setDoj(final String dojs) {
        this.doj = dojs;
    }

    /**
     * Get the location of the employee.
     *
     * @return The location of the employee.
     */
    public final Location getLocation() {
        return location;
    }

    /**
     * Set the location of the employee.
     *
     * @param locations The location to set.
     */
    public final void setLocation(final Location locations) {
        this.location = locations;
    }

    /**
     * Get the designation of the employee.
     *
     * @return The designation of the employee.
     */
    public final Designation getDesignation() {
        return designation;
    }

    /**
     * Set the designation of the employee.
     *
     * @param designations The designation to set.
     */
    public final void setDesignation(final Designation designations) {
        this.designation = designations;
    }

    /**
     * Get the contact number of the employee.
     *
     * @return The contact number of the employee.
     */
    public final Long getContactNo() {
        return contactNo;
    }

    /**
     * Set the contact number of the employee.
     *
     * @param contactNos The contact number to set.
     */
    public final void setContactNo(final Long contactNos) {
        this.contactNo = contactNos;
    }

    /**
     * Get the password of the employee.
     *
     * @return The password of the employee.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Set the password of the employee.
     *
     * @param passwords The password to set.
     */
    public final void setPassword(final String passwords) {
        this.password = passwords;
    }

    /**
     * Get the role of the employee.
     *
     * @return The role of the employee.
     */
    public final Role getRole() {
        return role;
    }

    /**
     * Set the role of the employee.
     *
     * @param roles The role to set.
     */
    public final void setRole(final Role roles) {
        this.role = roles;
    }

    /**
     * Get the skills of the employee.
     *
     * @return The skills of the employee.
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
     * Set the skills of the employee.
     *
     * @param skillslist The skills to set.
     */
    public final void setSkills(final List<String> skillslist) {
        if (skillslist.size() != 0) {
            this.skills = new ArrayList<>(skillslist);
        } else {
            this.skills = null;
        }
        ;
    }

    @Override
    public final String toString() {
        return "Employee [id=" + id + ", name=" + name
                + ", email=" + email + ", empId=" + empId + ", dob=" + dob
                + ", doj=" + doj + ", location=" + location
                + ", designation=" + designation + ", contactNo="
                + contactNo + ", password=" + password + ", role="
                + role + ", skills=" + skills + ", projectId="
                + projectId + "]";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(contactNo, designation, dob, doj,
                email, empId, id, location, name, password, role, skills);
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
        Employee other = (Employee) obj;
        return Objects.equals(contactNo, other.contactNo)
                && designation == other.designation
                && Objects.equals(dob, other.dob)
                && Objects.equals(doj, other.doj)
                && Objects.equals(email, other.email)
                && Objects.equals(empId, other.empId)
                && id == other.id
                && location == other.location
                && Objects.equals(name, other.name)
                && Objects.equals(password, other.password)
                && role == other.role
                && Objects.equals(skills, other.skills);
    }
}

