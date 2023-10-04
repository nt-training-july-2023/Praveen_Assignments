package com.backend.EMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
public class RequestResourceInDto {
    /**
     * The unique identifier for the RequestResource.
     */
    @NotBlank
    private Long id;
    /**
     * The employeeId of the RequestResource.
     */
    @NotBlank
    private Long employeeId;
    /**
     * The projectId of the RequestResource.
     */
    @NotBlank
    private Long projectId;
    /**
     * the ManagerId of the RequestResource.
     */
    @NotBlank
    private Long managerId;
    /**
     * The comment for the RequestResource.
     */
    @NotBlank
    private String comment;

    /**
     * @return the id
     */
    public final Long getId() {
        return id;
    }

    /**
     * @param idValue the id to set
     */
    public final void setId(final Long idValue) {
        this.id = idValue;
    }

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
     * @return the managerId
     */
    public final Long getManagerId() {
        return managerId;
    }

    /**
     * @param managerIdValue the managerId to set
     */
    public final void setManagerId(final Long managerIdValue) {
        this.managerId = managerIdValue;
    }

    /**
     * @return the comment
     */
    public final String getComment() {
        return comment;
    }

    /**
     * @param comments the comment to set
     */
    public final void setComment(final String comments) {
        this.comment = comments;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(comment, employeeId, id, managerId, projectId);
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
        RequestResourceInDto other = (RequestResourceInDto) obj;
        return Objects.equals(comment, other.comment)
                && Objects.equals(employeeId, other.employeeId)
                && Objects.equals(id, other.id)
                && Objects.equals(managerId, other.managerId)
                && Objects.equals(projectId, other.projectId);
    }

    @Override
    public final String toString() {
        return "RequestResourceInDto [id=" + id + ", employeeId="
                    + employeeId + ", projectId=" + projectId
                + ", managerId=" + managerId
                + ", comment=" + comment + "]";
    }

}
