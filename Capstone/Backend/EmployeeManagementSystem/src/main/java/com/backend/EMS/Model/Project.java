package com.backend.EMS.Model;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Entity class representing an Project.
 */
@Entity
@Table(name = "Project")
public class Project {
    /**
     * The unique identifier for the Project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * The name of the ProjectName.
     */
    @Column(unique = true)
    private String projectName;
    /**
     * The managerId of the project.
     */
    @Column
    private long managerId;
    /**
     * The Startdate of the  project.
     */
    @Column
    private String startDate;
    /**
     * The requiredskills of the project .
     */
    @Column
    private List<String> requiredSkills;
    /**
     * The description of the project.
     *
     */
    @Column
    private String description;
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
        if (requiredSkills != null){
        return new ArrayList<>(requiredSkills);
        }
        else {
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
    @Override
    public final String toString() {
        return "Project [id=" + id + ", projectName=" + projectName
                + ", managerId=" + managerId + ", startDate="
                + startDate + ", requiredSkills=" + requiredSkills
                + ", description=" + description + "]";
    }
    @Override
    public final int hashCode() {
        return Objects.hash(description, id, managerId,
                projectName, requiredSkills, startDate);
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
        Project other = (Project) obj;
        return Objects.equals(description, other.description)
                && id == other.id && managerId == other.managerId
                && Objects.equals(projectName, other.projectName)
                && Objects.equals(requiredSkills, other.requiredSkills)
                && Objects.equals(startDate, other.startDate);
    }

}
