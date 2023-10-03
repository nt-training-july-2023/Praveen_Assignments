package com.backend.EMS.Model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing an RequestResource.
 */
@Entity
@Table(name = "RequestResource")
public class RequestResource {

    /**
     * The unique identifier for the RequestResource.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The employeeId of the RequestResource.
     */
    private Long employeeId;
    /**
     * The projectId of the RequestResource.
     */
    @Column
    private Long projectId;
    /**
     * the ManagerId of the RequestResource.
     */
    @Column
    private Long managerId;
    /**
     * The comment for the RequestResource.
     */
    @Column(columnDefinition = "TEXT")
    private String comment;
    /**
     * @return the id
     */
    public final Long getId() {
        return id;
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
    public final String toString() {
        return "RequestResource [id=" + id + ", employeeId="
    + employeeId + ", projectId=" + projectId + ", managerId="
                + managerId + ", comment=" + comment + "]";
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
        RequestResource other = (RequestResource) obj;
        return Objects.equals(comment, other.comment)
                && Objects.equals(employeeId, other.employeeId)
                && Objects.equals(id, other.id)
                && Objects.equals(managerId, other.managerId)
                && Objects.equals(projectId, other.projectId);
    }



}
