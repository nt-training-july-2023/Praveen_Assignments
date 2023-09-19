package com.backend.EMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.EMS.Model.Project;

/**
 * Repository interface for managing Project entities in the database.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Find a project by its name.
     *
     * @param projectName The name of the project to search for.
     * @return The Project entity with the
        specified project name, if found; otherwise, null.
     */
    Project findByProjectName(String projectName);

    /**
     * Find all projects managed by a specific manager.
     *
     * @param managerId The ID of the manager to filter projects by.
     * @return A list of Project entities managed by the specified manager.
     */
    List<Project> findAllByManagerId(Long managerId);
}
