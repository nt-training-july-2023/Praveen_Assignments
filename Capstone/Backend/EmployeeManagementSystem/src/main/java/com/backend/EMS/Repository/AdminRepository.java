package com.backend.EMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.EMS.Model.Admin;

/**
 * Repository interface for managing admin data in the database.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Find an admin by their email.
     *
     * @param email The email to search for.
     * @return The admin with the specified email, or null if not found.
     */
    Admin findByEmail(String email);
    
    /**
     * Find an admin by their empId.
     * @param empId the Id to search for.
     * @return the amin with the specified Id, or null if not found.
     */
    Admin findByEmpId(String empId);
    /**
     * Find an admin by their contactNo.
     * @param contactNo the ContactNo to search for.
     * @return the admin with the specified contactNO,or null if not found.
     */
    Admin findByContactNo(long contactNo);
}
