package com.backend.EMS.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing an admin.
 */

@Entity
@Getter
@Setter
@Table(name = "Employee")
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
    
    @Column(nullable = false,unique=true)
    private String email;

    /**
     * The employee ID of the admin.
     */
    @Column(nullable = false,unique=true)
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
    @Enumerated(EnumType.STRING)
    private Location location;

    /**
     * The designation of the admin.
     */
    @Enumerated(EnumType.STRING)
    private Designation designation;

    /**
     * The contact number of the admin.
     */
    @Column(nullable = false,unique=true)
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
    
    @Enumerated(EnumType.STRING)
    private Role role;
}

