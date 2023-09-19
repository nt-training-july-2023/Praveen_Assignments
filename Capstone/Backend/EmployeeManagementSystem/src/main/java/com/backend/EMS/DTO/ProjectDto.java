package com.backend.EMS.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling project information.
 */
public class ProjectDto {
    /**
     * The id of the project.
     */
    private long id;
    /**
     * The name of the project.
     */
    private String projectName;

    /**
     * The manager's id of the project.
     */
    private long managerId;

    /**
     * The start date of the project.
     */
    private String startDate;

    /**
     * The required skills for the project.
     */
    private List<String> requiredSkills;

    /**
     * The description of the project.
     */
    private String description;

    /**
     * The name of the project head.
     */
    private String head;
    /**
     *
     */
    public ProjectDto() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @return the id
     */
    public final long getId() {
        return id;
    }
    /**
     * Set the idValue of the project Id.
     *
     * @param idValue The name of the project Id.
     */
    public final void setId(final Long idValue) {
        this.id = idValue;
    }
    /**
     * @return the managerId
     */
    public final long getManagerId() {
        return managerId;
    }
    /**
     * Set the ManagerId of the project .
     *
     * @param managerIdValue of the project .
     */
    public final void setManagerId(final Long managerIdValue) {
        this.managerId = managerIdValue;
    }

    /**
     * Get the name of the project.
     *
     * @return The name of the project.
     */
    public final String getProjectName() {
        return projectName;
    }

    /**
     * Set the name of the project.
     *
     * @param projectNameValue The name of the project.
     */
    public final void setProjectName(final String projectNameValue) {
        this.projectName = projectNameValue;
    }
    /**
     * Get the start date of the project.
     *
     * @return The start date of the project.
     */
    public final String getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the project.
     *
     * @param startDateValue The start date of the project.
     */
    public final void setStartDate(final String startDateValue) {
        this.startDate = startDateValue;
    }

    /**
     * Get the required skills for the project.
     *
     * @return The required skills for the project.
     */
    public final List<String> getRequiredSkills() {
        if (requiredSkills != null) {
            return new ArrayList<>(requiredSkills);
        } else {
            return null;
        }
    }

    /**
     * Set the required skills for the project.
     *
     * @param requiredSkillsValue The required skills for the project.
     */
    public final void setRequiredSkills(final
            List<String> requiredSkillsValue) {
        if (requiredSkillsValue.size()!=0){
        this.requiredSkills = new ArrayList<>(requiredSkillsValue);
        }
        else {
            this.requiredSkills=null;
        }
    }

    /**
     * Get the description of the project.
     *
     * @return The description of the project.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set the description of the project.
     *
     * @param descriptionValue The description of the project.
     */
    public final void setDescription(final String descriptionValue) {
        this.description = descriptionValue;
    }

    /**
     * Get the name of the project head.
     *
     * @return The name of the project head.
     */
    public final String getHead() {
        return head;
    }

    /**
     * Set the name of the project head.
     *
     * @param headValue The name of the project head.
     */
    public final void setHead(final String headValue) {
        this.head = headValue;
    }

    /**
     * Generate a string representation of this object.
     *
     * @return A string representation of the object.
     */
    @Override
    public final String toString() {
        return "ProjectDto [id=" + id + ", projectName="
                + projectName + ", managerId=" + managerId
                + ", startDate=" + startDate + ", requiredSkills="
                + requiredSkills + ", description=" + description
                + ", head=" + head + "]";
    }

    /**
     * Generate a hash code for this object based on its properties.
     *
     * @return The generated hash code.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(description, head, id, managerId,
                projectName, requiredSkills, startDate);
    }

    /**
     * Compare this object with another object for
     *  equality based on its properties.
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
        ProjectDto other = (ProjectDto) obj;
        return Objects.equals(description, other.description)
                && Objects.equals(head, other.head) && id == other.id
                && managerId == other.managerId
                && Objects.equals(projectName, other.projectName)
                && Objects.equals(requiredSkills, other.requiredSkills)
                && Objects.equals(startDate, other.startDate);
    }
}

