package com.backend.EMS.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Role;

/**
 * Repository interface for managing admin data in the database.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Find an admin by their email.
     *
     * @param email The email to search for.
     * @return The admin with the specified email, or null if not found.
     */
    Employee findByEmail(String email);

    /**
     * Find an admin by their empId.
     *
     * @param empId the Id to search for.
     * @return the amin with the specified Id, or null if not found.
     */
    Employee findByEmpId(String empId);

    /**
     * Find an admin by their contactNo.
     *
     * @param contactNo the ContactNo to search for.
     * @return the admin with the specified contactNO,or null if not found.
     */
    Employee findByContactNo(long contactNo);

    /**
     * Find an admin by their Role.
     *
     * @param role the role to search for.
     * @return the admin with specified role, or null if not found.
     */
    List<Employee> findByRole(Role role);
     /**
      *
      * @param roles the roles to search for.
      * @return employees with the roles
      */
    List<Employee> findByRoleIn(List<Role> roles);

    /**
     *  Find an admin by their email.
     *
     * @param email the email to search for.
     * @return the employee with the email
     */
    Long findIdByEmail(String email);
    /**
     *
     * @param id the project to search.
     * @return the employee with project id
     */
    List<Employee> findEmployeesByProjectId(long id);

}
