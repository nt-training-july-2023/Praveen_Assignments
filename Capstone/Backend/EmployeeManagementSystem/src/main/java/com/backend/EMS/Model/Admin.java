package com.backend.EMS.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing an admin.
 */
@Data
@Entity
@Table(name = "Admin")
public class Admin {

    /**
     * The unique identifier for the admin.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the admin.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email of the admin.
     */
    @Column(nullable = false)
    private String email;

    /**
     * The employee ID of the admin.
     */
    @Column(nullable = false)
    private String empId;

    /**
     * The date of birth of the admin.
     */
    @Column(nullable = false)
    private String dob;

    /**
     * The date of joining of the admin.
     */
    @Column(nullable = false)
    private String doj;

    /**
     * The location of the admin.
     */
    @Column(nullable = false)
    private String location;

    /**
     * The designation of the admin.
     */
    @Column(nullable = false)
    private String designation;

    /**
     * The contact number of the admin.
     */
    @Column(nullable = false)
    private Long contactNo;

    /**
     * The password of the admin.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The confirmation password for the admin.
     */
    @Column(nullable = false)
    private String confirmPassword;
}

