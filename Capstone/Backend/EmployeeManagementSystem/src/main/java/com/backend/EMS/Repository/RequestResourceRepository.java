package com.backend.EMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.backend.EMS.Model.RequestResource;

/**
 * Repository interface for managing RequestResource entities.
 */
@EnableJpaRepositories
public interface RequestResourceRepository extends
            JpaRepository<RequestResource, Long> {

    /**
     * Retrieve a list of RequestResources by the manager's ID.
     *
     * @param employeeId The ID of the manager.
     * @return A list of RequestResource objects associated
     * with the manager's ID.
     */
    List<RequestResource> findByManagerId(Long employeeId);

    /**
     * Retrieve a RequestResource by both employee's ID and manager's ID.
     *
     * @param employeeId The ID of the employee.
     * @param managerId  The ID of the manager.
     * @return The RequestResource associated with the given employee
     * and manager IDs.
     */
    RequestResource findByEmployeeIdAndManagerId(Long employeeId,
            Long managerId);

    /**
     * Delete a RequestResource by employee's ID.
     *
     * @param employeeId The ID of the employee whose RequestResource
     * should be deleted.
     */
    void deleteByEmployeeId(Long employeeId);

    /**
     * Retrieve a list of RequestResources by employee's ID.
     *
     * @param id The ID of the employee.
     * @return A list of RequestResource objects associated
     * with the employee's ID.
     */
    List<RequestResource> findByEmployeeId(long id);
}
