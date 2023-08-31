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
}
