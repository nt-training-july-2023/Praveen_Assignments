package com.backend.EMS.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Data Transfer Object (DTO) for handling project information.
 */
public class ProjectOutDto {
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
     * The names of the employees.
     */
    private List<String> team;

    /**
     * @return the id
     */
    public final long getId() {
        return id;
    }

    /**
     * @param ids the id to set
     */
    public final void setId(final long ids) {
        this.id = ids;
    }

    /**
     * @return the projectName
     */
    public final String getProjectName() {
        return projectName;
    }

    /**
     * @param projectNames the projectName to set
     */
    public final void setProjectName(final String projectNames) {
        this.projectName = projectNames;
    }

    /**
     * @return the managerId
     */
    public final long getManagerId() {
        return managerId;
    }

    /**
     * @param managerIds the managerId to set
     */
    public final void setManagerId(final long managerIds) {
        this.managerId = managerIds;
    }

    /**
     * @return the startDate
     */
    public final String getStartDate() {
        return startDate;
    }

    /**
     * @param startDates the startDate to set
     */
    public final void setStartDate(final String startDates) {
        this.startDate = startDates;
    }

    /**
     * @return the requiredSkills
     */
    public final List<String> getRequiredSkills() {
        if (requiredSkills != null) {
        return new ArrayList<>(requiredSkills);
        } else {
            return null;
        }
    }

    /**
     * @param requiredSkillss the requiredSkills to set
     */
    public final void setRequiredSkills(final List<String> requiredSkillss) {
        if (requiredSkillss.size() != 0) {
            this.requiredSkills = new ArrayList<>(requiredSkillss);
        } else {
            this.requiredSkills = null;
        }
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param descriptions the description to set
     */
    public final void setDescription(final String descriptions) {
        this.description = descriptions;
    }

    /**
     * @return the head
     */
    public final String getHead() {
        return head;
    }

    /**
     * @param heads the head to set
     */
    public final void setHead(final String heads) {
        this.head = heads;
    }

    /**
     * @return the team
     */
    public final List<String> getTeam() {
        if (team != null) {
        return new ArrayList<>(team);
        } else {
            return null;
        }
    }

    /**
     * @param teams the team to set
     */
    public final void setTeam(final List<String> teams) {
        if (teams.size() != 0) {
            this.team = new ArrayList<>(teams);
            } else {
                this.team = null;
            }
    }

    @Override
    public final String toString() {
        return "ProjectOutDto [id=" + id + ", projectName=" + projectName
                + ", managerId=" + managerId + ", startDate="
                + startDate + ", requiredSkills=" + requiredSkills
                + ", description=" + description + ", head=" + head
                + ", team=" + team + "]";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(description, head, id, managerId,
                projectName, requiredSkills, startDate, team);
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
        ProjectOutDto other = (ProjectOutDto) obj;
        return Objects.equals(description, other.description)
                && Objects.equals(head, other.head) && id == other.id
                && managerId == other.managerId
                && Objects.equals(projectName, other.projectName)
                && Objects.equals(requiredSkills, other.requiredSkills)
                && Objects.equals(startDate, other.startDate)
                && Objects.equals(team, other.team);
    }
}
