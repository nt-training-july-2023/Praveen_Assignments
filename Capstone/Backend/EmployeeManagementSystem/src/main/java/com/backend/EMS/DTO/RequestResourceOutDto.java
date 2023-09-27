package com.backend.EMS.DTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for handling response information.
 */
public class RequestResourceOutDto {
    /**
     * The unique identifier for the RequestResource.
     */
    private Long id;
    /**
     * The employeeId of the RequestResource.
     */
    private Long employeeId;
    /**
     * employeeEmpId of the RequestResource.
     */
    private String employeeEmpId;
    /**
     * The projectId of the RequestResource.
     */
    private Long projectId;
    /**
     * the ManagerId of the RequestResource.
     */
    private Long managerId;
    /**
     * managerEmpId of the RequestResource.
     */
    private String managerEmpId;
    /**
     * the employeeName of the RequestResource.
     */
    private String employeeName;
    /**
     * the managername of the RequestResource.
     */
    private String managerName;
    /**
     * the projectname of the RequestResource.
     */
    private String projectName;
    /**
     * The comment for the RequestResource.
     */
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
     * @return the employeeEmpId
     */
    public final String getEmployeeEmpId() {
        return employeeEmpId;
    }
    /**
     * @param employeeEmpIdValue the employeeEmpId to set
     */
    public final void setEmployeeEmpId(final String employeeEmpIdValue) {
        this.employeeEmpId = employeeEmpIdValue;
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
     * @return the managerEmpId
     */
    public final String getManagerEmpId() {
        return managerEmpId;
    }
    /**
     * @param managerEmpIdValue the managerEmpId to set
     */
    public final void setManagerEmpId(final String managerEmpIdValue) {
        this.managerEmpId = managerEmpIdValue;
    }
    /**
     * @return the employeeName
     */
    public final String getEmployeeName() {
        return employeeName;
    }
    /**
     * @param employeeNameValue the employeeName to set
     */
    public final void setEmployeeName(final String employeeNameValue) {
        this.employeeName = employeeNameValue;
    }
    /**
     * @return the managerName
     */
    public final String getManagerName() {
        return managerName;
    }
    /**
     * @param managerNameValue the managerName to set
     */
    public final void setManagerName(final String managerNameValue) {
        this.managerName = managerNameValue;
    }
    /**
     * @return the projectName
     */
    public final String getProjectName() {
        return projectName;
    }
    /**
     * @param projectNameValue the projectName to set
     */
    public final void setProjectName(final String projectNameValue) {
        this.projectName = projectNameValue;
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
        return Objects.hash(comment, employeeEmpId, employeeId,
                employeeName, id, managerEmpId, managerId, managerName,
                projectId, projectName);
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
        RequestResourceOutDto other = (RequestResourceOutDto) obj;
        return Objects.equals(comment, other.comment)
                && Objects.equals(employeeEmpId, other.employeeEmpId)
                && Objects.equals(employeeId, other.employeeId)
                && Objects.equals(employeeName, other.employeeName)
                && Objects.equals(id, other.id)
                && Objects.equals(managerEmpId, other.managerEmpId)
                && Objects.equals(managerId, other.managerId)
                && Objects.equals(managerName, other.managerName)
                && Objects.equals(projectId, other.projectId)
                && Objects.equals(projectName, other.projectName);
    }
    @Override
    public final String toString() {
        return "RequestResourceOutDto [id=" + id + ", employeeId="
                + employeeId + ", employeeEmpId=" + employeeEmpId
                + ", projectId=" + projectId + ", managerId="
                + managerId + ", managerEmpId=" + managerEmpId
                + ", employeeName=" + employeeName + ", managerName="
                + managerName + ", projectName=" + projectName
                + ", comment=" + comment + "]";
    }
}
