package com.backend.EMS.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) for handling employee
 *  information to be sent as output.
 */
public class EmployeeOutDto {
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
    @Pattern(regexp = ".+@nucleusteq\\.com$",
            message = "Email must end with @nucleusteq.com")
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
     * The manager ID of the employee.
     */
    private Long managerId;
    /**
     * The project ID of the employee.
     */
    private Long projectId;
    /**
     * @return the projectId
     */
    public final Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectIdValue the projectId to set
     */
    public final void setProjectId(final Long projectIdValue) {
        this.projectId = projectIdValue;
    }

    /**
     * The name of the manager of the employee.
     */
    private String managerName;
    /**
     * The name of the project of the employee.
     */
    private String projectName;
    /**
     * The skills of the employee.
     */
    private List<String> skills;

    /**
     * Get the skills of the employee.
     *
     * @return The skills of the employee.
     */
    private List<String> team;
    /**
     * @return the team
     */
    public final List<String> getTeam() {
        if (team != null){
        return new ArrayList<>(team);
        }
        else {
            return null;
        }
    }

    /**
     * @param teamList the team to set
     */
    public final void setTeam(final List<String> teamList) {
        if (teamList.size()!=0){
        this.team = new ArrayList<>(teamList);
        }
        else {
            this.skills=null;
        }
    }
    /**
     *
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
     * Set the skills of the employee.
     *
     * @param skillsList The skills of the employee.
     */
    public final void setSkills(final List<String> skillsList) {
        if (skillsList.size() != 0) {
            this.skills = new ArrayList<>(skillsList);
        } else {
            this.skills = null;
        }
    }

    /**
     * Get the name of the project of the employee.
     *
     * @return The name of the project of the employee.
     */
    public final String getProjectName() {
        return projectName;
    }

    /**
     * Set the name of the project of the employee.
     *
     * @param projectNameValue The name of the project of the employee.
     */
    public final void setProjectName(final String projectNameValue) {
        this.projectName = projectNameValue;
    }

    /**
     * Get the id of the employee.
     *
     * @return The id of the employee.
     */
    public final Long getId() {
        return id;
    }

    /**
     * Set the id of the employee.
     *
     * @param idValue The id of the employee.
     */
    public final void setId(final Long idValue) {
        this.id = idValue;
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
     * @param nameValue The name of the employee.
     */
    public final void setName(final String nameValue) {
        this.name = nameValue;
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
     * @param emailValue The email of the employee.
     */
    public final void setEmail(final String emailValue) {
        this.email = emailValue;
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
     * @param empIdValue The employee ID of the employee.
     */
    public final void setEmpId(final String empIdValue) {
        this.empId = empIdValue;
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
     * @param dobValue The date of birth of the employee.
     */
    public final void setDob(final String dobValue) {
        this.dob = dobValue;
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
     * @param dojValue The date of joining of the employee.
     */
    public final void setDoj(final String dojValue) {
        this.doj = dojValue;
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
     * @param locationValue The location of the employee.
     */
    public final void setLocation(final Location locationValue) {
        this.location = locationValue;
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
     * @param designationValue The designation of the employee.
     */
    public final void setDesignation(final Designation designationValue) {
        this.designation = designationValue;
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
     * @param contactNoValue The contact number of the employee.
     */
    public final void setContactNo(final Long contactNoValue) {
        this.contactNo = contactNoValue;
    }

    /**
     * Get the manager ID of the employee.
     *
     * @return The manager ID of the employee.
     */
    public final Long getManagerId() {
        return managerId;
    }

    /**
     * Set the manager ID of the employee.
     *
     * @param managerIdValue The manager ID of the employee.
     */
    public final void setManagerId(final Long managerIdValue) {
        this.managerId = managerIdValue;
    }

    /**
     * Get the name of the manager of the employee.
     *
     * @return The name of the manager of the employee.
     */
    public final String getManagerName() {
        return managerName;
    }

    /**
     * Set the name of the manager of the employee.
     *
     * @param managerNameValue The name of the manager of the employee.
     */
    public final void setManagerName(final String managerNameValue) {
        this.managerName = managerNameValue;
    }
    @Override
    public final String toString() {
        return "EmployeeOutDto [id=" + id + ", name=" + name + ", email="
                            + email + ", empId=" + empId + ", dob=" + dob
                + ", doj=" + doj + ", location=" + location + ", designation="
                            + designation + ", contactNo="
                + contactNo + ", managerId=" + managerId + ", projectId="
                            + projectId + ", managerName=" + managerName
                + ", projectName=" + projectName + ", skills="
                            + skills + ", team=" + team + "]";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(contactNo, designation, dob, doj, email, empId,
                id, location, managerId, managerName, name,
                projectId, projectName, skills, team);
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
        EmployeeOutDto other = (EmployeeOutDto) obj;
        return Objects.equals(contactNo, other.contactNo)
                && designation == other.designation
                && Objects.equals(dob, other.dob)
                && Objects.equals(doj, other.doj)
                && Objects.equals(email, other.email)
                && Objects.equals(empId, other.empId)
                && Objects.equals(id, other.id)
                && location == other.location
                && Objects.equals(managerId, other.managerId)
                && Objects.equals(managerName, other.managerName)
                && Objects.equals(name, other.name)
                && Objects.equals(projectId, other.projectId)
                && Objects.equals(projectName, other.projectName)
                && Objects.equals(skills, other.skills)
                && Objects.equals(team, other.team);
    }

    /**
     *
     */
    public EmployeeOutDto() {
        super();
        // TODO Auto-generated constructor stub
    }
}

